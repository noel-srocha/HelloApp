package dev.noelsrocha.helloapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.ui.login.FormularioLoginTela
import dev.noelsrocha.helloapp.ui.login.FormularioLoginViewModel
import dev.noelsrocha.helloapp.ui.login.LoginTela
import dev.noelsrocha.helloapp.ui.login.LoginViewModel
import dev.noelsrocha.helloapp.ui.navegarLimpo
import kotlinx.coroutines.launch

fun NavGraphBuilder.loginGraph(
    onNavegarParaHome: () -> Unit,
    onNavegarParaFormularioLogin: () -> Unit,
    onNavegarParaLogin: () -> Unit,
) {
    navigation(
        startDestination = DestinosHelloApp.Login.rota,
        route = DestinosHelloApp.LoginGraph.rota
    ) {

        composable(
            route = DestinosHelloApp.Login.rota,
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            if (state.logado) {
                LaunchedEffect(Unit) { onNavegarParaHome() }
            }

            val coroutineScope = rememberCoroutineScope()

            LoginTela(
                state = state,
                onClickLogar = {
                    coroutineScope.launch {
                        viewModel.tentaLogar()
                    }
                },
                onClickCriarLogin = onNavegarParaFormularioLogin
            )
        }

        composable(
            route = DestinosHelloApp.FormularioLogin.rota,
        ) {
            val viewModel = hiltViewModel<FormularioLoginViewModel>()
            val state by viewModel.uiState.collectAsState()
            val coroutineScope = rememberCoroutineScope()

            FormularioLoginTela(
                state = state,
                onSalvar = {
                    coroutineScope.launch {
                        viewModel.salvarLogin()
                    }

                    onNavegarParaLogin()
                }
            )
        }
    }
}