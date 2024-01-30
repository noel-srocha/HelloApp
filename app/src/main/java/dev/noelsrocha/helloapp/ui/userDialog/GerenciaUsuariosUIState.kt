package dev.noelsrocha.helloapp.ui.userDialog

import dev.noelsrocha.helloapp.models.Usuario

data class GerenciaUsuariosUIState(
    val usuarios: List<Usuario> = emptyList()
)