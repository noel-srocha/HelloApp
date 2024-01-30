package dev.noelsrocha.helloapp.ui.userDialog

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import dev.noelsrocha.helloapp.util.ID_USUARIO_ATUAL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioUsuarioViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStore: DataStore<Preferences>,
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val idUsuario = savedStateHandle.get<Int>(ID_USUARIO_ATUAL)

    private val _uiState = MutableStateFlow(FormularioUsuarioUIState())
    val uiState: StateFlow<FormularioUsuarioUIState>
        get() = _uiState.asStateFlow()

    init {
        idUsuario?.let { id ->
            viewModelScope.launch {
                usuarioDao.buscarPorId(id).first()?.let { usuarioBuscado ->
                    _uiState.value = _uiState.value.copy(
                        nome = usuarioBuscado.nome,
                        nomeUsuario = usuarioBuscado.nomeUsuario,
                        senha = usuarioBuscado.senha
                    )
                }
            }
        }

        _uiState.update { state ->
            state.copy(onNomeMudou = {
                _uiState.value = _uiState.value.copy(
                    nome = it
                )
            })
        }
    }

    fun onClickMostraMensagemExclusao() {
        _uiState.value = _uiState.value.copy(
            mostraMensagemExclusao = true
        )
    }
}