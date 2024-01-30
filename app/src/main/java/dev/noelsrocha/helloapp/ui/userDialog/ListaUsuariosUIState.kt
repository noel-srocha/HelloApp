package dev.noelsrocha.helloapp.ui.userDialog

import dev.noelsrocha.helloapp.models.Usuario

data class ListaUsuariosUIState(
    val nomeDeUsuario: String? = null,
    val nome: String? = null,
    val outrasContas: List<Usuario> = emptyList()
)