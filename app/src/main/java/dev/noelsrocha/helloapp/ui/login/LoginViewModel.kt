package dev.noelsrocha.helloapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
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
                onErro = {
                    _uiState.value = _uiState.value.copy(
                        exibirErro = it
                    )
                },
            )
        }
    }

    suspend fun tentaLogar() {
        dataStore.data.collect { preferences ->
            val senha = preferences[PreferencesKey.SENHA]
            val usuario = preferences[PreferencesKey.USUARIO]

            if (usuario == _uiState.value.usuario && senha == _uiState.value.senha) {
                dataStore.edit {
                    it[PreferencesKey.LOGADO] = true
                }

                logaUsuario()
            } else {
                _uiState.value.onErro(true)
            }
        }

        dataStore.edit { preferences ->
            preferences[PreferencesKey.LOGADO] = true
        }
    }

    private fun logaUsuario() {
        _uiState.value = _uiState.value.copy(logado = true)
    }
}


