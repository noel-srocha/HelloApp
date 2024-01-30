package dev.noelsrocha.helloapp.ui.userDialog

data class FormularioUsuarioUIState(
    val nomeUsuario: String = "",
    val nome: String = "",
    val senha: String = "",
    val onNomeMudou: (String) -> Unit = {},
    val mostraMensagemExclusao: Boolean = false,
    val mostraMensagemExclusaoMudou: (Boolean) -> Unit = {},
)
