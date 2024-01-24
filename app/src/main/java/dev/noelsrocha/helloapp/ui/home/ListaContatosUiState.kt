package dev.noelsrocha.helloapp.ui.home

import dev.noelsrocha.helloapp.models.Contato

data class ListaContatosUiState(
    val pesquisaContato: String = "",
    val contatos: List<Contato> = emptyList(),
    val logado: Boolean = true,
    val nomeUsuario: String = "",
    val abrirLogoutDialog: Boolean = false,
    val onAbrirLogoutDialogMudou: (valor: Boolean) -> Unit = {}
)