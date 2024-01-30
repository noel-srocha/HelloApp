package dev.noelsrocha.helloapp.ui.userDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GerenciaUsuariosViewModel @Inject constructor(
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(GerenciaUsuariosUIState())
    val uiState: StateFlow<GerenciaUsuariosUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaDados()
        }
    }

    private suspend fun carregaDados() {
        usuarioDao.buscarTodos().collect {
            _uiState.value = _uiState.value.copy(
                usuarios = it
            )
        }
    }
}