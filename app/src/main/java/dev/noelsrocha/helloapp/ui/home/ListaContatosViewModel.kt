package dev.noelsrocha.helloapp.ui.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import dev.noelsrocha.helloapp.preferences.PreferencesKey.USUARIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaContatosViewModel @Inject constructor(
    private val contatoDao: ContatoDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListaContatosUiState())
    val uiState: StateFlow<ListaContatosUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val contatos = contatoDao.buscaTodos()
            contatos.collect { contatosBuscados ->
                _uiState.value = _uiState.value.copy(
                    contatos = contatosBuscados
                )
            }
        }

        viewModelScope.launch {
            carregaNomeUsuario()
        }

        _uiState.update { state ->
            state.copy(
                onAbrirLogoutDialogMudou = {
                    _uiState.value = _uiState.value.copy(
                        abrirLogoutDialog = it
                    )
                }
            )
        }
    }

    private suspend fun carregaNomeUsuario() {
        val usuario = dataStore.data.first()[USUARIO]
        usuario?.let {
            _uiState.value = _uiState.value.copy(
                nomeUsuario = it
            )
        }
    }

    fun pesquisaContato(pesquisaContato: String) {
        _uiState.update { it.copy(pesquisaContato = pesquisaContato) }

        viewModelScope.launch {
            val contatos = contatoDao.buscaPorNome(pesquisaContato)
            contatos.collect { contatosBuscados ->
                _uiState.value = _uiState.value.copy(
                    contatos = contatosBuscados
                )
            }
        }
    }

    suspend fun desloga() {
        dataStore.edit { preferences -> preferences[PreferencesKey.LOGADO] = false }
    }
}