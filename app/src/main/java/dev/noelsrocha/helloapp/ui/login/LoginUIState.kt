package dev.noelsrocha.helloapp.ui.login

data class LoginUIState(
    val usuario: String = "",
    val senha: String = "",
    val exibirErro: Boolean = false,
    val onUsuarioMudou: (String) -> Unit = {},
    val onSenhaMudou: (String) -> Unit = {},
    val onClickLogar: () -> Unit = {},
    val logado: Boolean = false
)