package dev.noelsrocha.helloapp.ui.search

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BuscaContatosViewModel @Inject constructor(
    private val contatoDao: ContatoDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BuscaContatosUIState()
    )
    val uiState: StateFlow<BuscaContatosUIState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(onValorBuscaMudou = {
                _uiState.value = _uiState.value.copy(
                    valorBusca = it
                )
                buscaContatosPorValor()
            })
        }

    }

    fun buscaContatosPorValor() {

    }
}