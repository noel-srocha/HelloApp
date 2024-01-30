package dev.noelsrocha.helloapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onUsuarioMudou = {
                    _uiState.value = _uiState.value.copy(
                        usuario = it
                    )
                },
                onSenhaMudou = {
                    _uiState.value = _uiState.value.copy(
                        senha = it
                    )
                },
            )
        }
    }

    suspend fun tentaLogar() {
        val usuarioBuscado = usuarioDao.buscarPorNome(_uiState.value.usuario).first()

        if (usuarioBuscado?.nomeUsuario != null && usuarioBuscado.senha == _uiState.value.senha) {
            dataStore.edit {
                it[PreferencesKey.LOGADO] = true
                it[PreferencesKey.USUARIO_ATUAL_ID] = usuarioBuscado.id
            }

            logarUsuario()
        }
    }

    private fun logarUsuario() {
        _uiState.value = _uiState.value.copy(logado = true)
    }
}


