package dev.noelsrocha.helloapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.util.ID_CONTATO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetalhesContatoViewlModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contatoDao: ContatoDao
) : ViewModel() {

    private val idContato = savedStateHandle.get<Long>(ID_CONTATO)

    private val _uiState = MutableStateFlow(DetalhesContatoUIState())
    val uiState: StateFlow<DetalhesContatoUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            carregaContato()
        }
    }

    private suspend fun carregaContato() {
        idContato?.let {
            val contato = contatoDao.buscaPorId(idContato)

            contato.collect {
                it?.let {
                    with(it) {
                        _uiState.value = _uiState.value.copy(
                            id = id,
                            nome = nome,
                            sobrenome = sobrenome,
                            aniversario = aniversario,
                            fotoPerfil = fotoPerfil,
                            telefone = telefone
                        )
                    }
                }
            }
        }
    }

    suspend fun removeContato() {
        idContato?.let { contatoDao.deleta(it) }
    }
}
