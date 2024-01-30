package dev.noelsrocha.helloapp.ui.login

data class FormularioLoginUIState(
    val nome: String = "",
    val usuario: String = "",
    val senha: String = "",
    val onNomeMudou: (String) -> Unit = {},
    val onUsuarioMudou: (String) -> Unit = {},
    val onSenhaMudou: (String) -> Unit = {},
    val onClickSalvar: () -> Unit = {}
)