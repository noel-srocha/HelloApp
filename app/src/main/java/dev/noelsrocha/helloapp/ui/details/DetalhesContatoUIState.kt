package dev.noelsrocha.helloapp.ui.details

import java.util.*

data class DetalhesContatoUIState(
    val id: Long = 0L,
    val nome: String = "",
    val sobrenome: String = "",
    val telefone: String = "",
    val fotoPerfil: String = "",
    val aniversario: Date? = null,
)
