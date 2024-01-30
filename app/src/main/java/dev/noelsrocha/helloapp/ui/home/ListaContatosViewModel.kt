package dev.noelsrocha.helloapp.ui.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaContatosViewModel @Inject constructor(
    private val contatoDao: ContatoDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListaContatosUIState())
    val uiState: StateFlow<ListaContatosUIState>
        get() = _uiState.asStateFlow()

    init {
        buscarContatos()
    }

    private fun buscarContatos() {
        viewModelScope.launch {
            dataStore.data.first()[PreferencesKey.USUARIO_ATUAL_ID]?.let {
                _uiState.value = _uiState.value.copy(
                    usuarioID = it
                )
            }
        }

        viewModelScope.launch {
            val contatos = _uiState.value.usuarioID?.let { contatoDao.buscaTodosPorUsuario(it) }
            contatos?.collect { contatosBuscados ->
                _uiState.value = _uiState.value.copy(
                    contatos = contatosBuscados
                )
            }
        }
    }
}