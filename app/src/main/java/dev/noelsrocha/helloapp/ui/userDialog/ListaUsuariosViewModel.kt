package dev.noelsrocha.helloapp.ui.userDialog

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import dev.noelsrocha.helloapp.util.ID_USUARIO_ATUAL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaUsuariosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStore<Preferences>,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val idUsuarioAtual = savedStateHandle.get<Int>(ID_USUARIO_ATUAL)

    private val _uiState = MutableStateFlow(ListaUsuariosUIState())
    val uiState: StateFlow<ListaUsuariosUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaDados()
        }
    }

    private suspend fun carregaDados() {
        idUsuarioAtual?.let { idUsuario ->
            val usuarioLogado = usuarioDao.buscarPorId(idUsuario).first()
            usuarioLogado?.let {
                _uiState.value = _uiState.value.copy(
                    nomeDeUsuario = it.nomeUsuario,
                    nome = it.nome
                )
            }
        }

        usuarioDao.buscarTodos().collect { outrasContas ->
            _uiState.value = _uiState.value.copy(
                outrasContas = outrasContas.filter {
                    it.id != idUsuarioAtual
                }
            )
        }
    }

    suspend fun atualizarUsuarioLogado(novoUsuario: Int) {
        dataStore.edit {
            it[PreferencesKey.USUARIO_ATUAL_ID] = novoUsuario
        }
    }
}