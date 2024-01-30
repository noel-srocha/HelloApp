package dev.noelsrocha.helloapp.ui.form

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noelsrocha.helloapp.R
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.extensions.converteParaDate
import dev.noelsrocha.helloapp.extensions.converteParaString
import dev.noelsrocha.helloapp.models.Contato
import dev.noelsrocha.helloapp.preferences.PreferencesKey
import dev.noelsrocha.helloapp.util.ID_CONTATO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioContatoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contatoDao: ContatoDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val idContato = savedStateHandle.get<Long>(ID_CONTATO)

    private val _uiState = MutableStateFlow(FormularioContatoUIState())
    val uiState: StateFlow<FormularioContatoUIState>
        get() = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            carregarContato()
        }

        _uiState.update { state ->
            state.copy(onNomeMudou = {
                _uiState.value = _uiState.value.copy(
                    nome = it
                )
            }, onSobrenomeMudou = {
                _uiState.value = _uiState.value.copy(
                    sobrenome = it
                )
            }, onTelefoneMudou = {
                _uiState.value = _uiState.value.copy(
                    telefone = it
                )
            }, onFotoPerfilMudou = {
                _uiState.value = _uiState.value.copy(
                    fotoPerfil = it
                )
            }, onAniversarioMudou = {
                _uiState.value = _uiState.value.copy(
                    aniversario = it.converteParaDate(), mostrarCaixaDialogoData = false
                )
            }, onMostrarCaixaDialogoImagem = {
                _uiState.value = _uiState.value.copy(
                    mostrarCaixaDialogoImagem = it
                )
            }, onMostrarCaixaDialogoData = {
                _uiState.value = _uiState.value.copy(
                    mostrarCaixaDialogoData = it
                )
            })
        }
    }

    private suspend fun carregarContato() {
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
                            telefone = telefone,
                            tituloAppbar = R.string.titulo_editar_contato
                        )
                    }
                }
            }
        }
    }

    fun defineTextoAniversario(textoAniversario: String) {
        val textoAniversairo = _uiState.value.aniversario?.converteParaString() ?: textoAniversario

        _uiState.update {
            it.copy(textoAniversairo = textoAniversairo)
        }
    }

    fun carregarImagem(url: String) {
        _uiState.value = _uiState.value.copy(
            fotoPerfil = url, mostrarCaixaDialogoImagem = false
        )
    }

    suspend fun salvar() {
        dataStore.data.first()[PreferencesKey.USUARIO_ATUAL_ID]?.let {
            contatoDao.insere(
                with(uiState.value) {
                    Contato(
                        id = id,
                        nome = nome,
                        sobrenome = sobrenome,
                        aniversario = aniversario,
                        fotoPerfil = fotoPerfil,
                        telefone = telefone,
                        usuarioID = it
                    )
                }
            )
        }
    }
}
