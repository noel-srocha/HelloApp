package dev.noelsrocha.helloapp.ui.splashscreen

data class SplashScreenUiState(
    val appState: AppState = AppState.Carregando
)

sealed class AppState {
    object Carregando : AppState()
    object Logado : AppState()
    object Deslogado : AppState()
}