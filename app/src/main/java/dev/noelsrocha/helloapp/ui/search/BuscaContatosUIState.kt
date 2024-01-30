package dev.noelsrocha.helloapp.ui.search

import dev.noelsrocha.helloapp.models.Contato

data class BuscaContatosUIState(
    val contatos: List<Contato> = emptyList(),
    val usuarioAtual: String? = null,
    val valorBusca: String = "",
    val onValorBuscaMudou: (String) -> Unit = {}
)