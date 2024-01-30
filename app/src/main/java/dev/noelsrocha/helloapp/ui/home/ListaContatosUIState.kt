package dev.noelsrocha.helloapp.ui.home

import dev.noelsrocha.helloapp.models.Contato

data class ListaContatosUIState(
    val contatos: List<Contato> = emptyList(),
    val usuarioID: Int? = null
)