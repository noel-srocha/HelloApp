package dev.noelsrocha.helloapp.ui.home

import dev.noelsrocha.helloapp.data.Contato

data class ListaContatosUiState(
    val contatos: List<Contato> = emptyList(),
    val logado: Boolean = true
)