package dev.noelsrocha.helloapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.ui.home.ListaContatosTela
import dev.noelsrocha.helloapp.ui.home.ListaContatosViewModel
import dev.noelsrocha.helloapp.ui.navegaParaDetalhes
import dev.noelsrocha.helloapp.ui.navegaParaFormularioContato
import dev.noelsrocha.helloapp.ui.navegaParaLoginDeslogado
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = DestinosHelloApp.ListaContatos.rota,
        route = DestinosHelloApp.HomeGraph.rota,
    ) {
        composable(route = DestinosHelloApp.ListaContatos.rota) {
            val viewModel = hiltViewModel<ListaContatosViewModel>()
            val state by viewModel.uiState.collectAsState()

            val coroutineScope = rememberCoroutineScope()

            ListaContatosTela(
                state = state,
                onClickAbreDetalhes = { idContato ->
                    navController.navegaParaDetalhes(idContato)
                },
                onClickAbreCadastro = {
                    navController.navegaParaFormularioContato()
                },
                onClickDesloga = {
                    coroutineScope.launch {
                        viewModel.desloga()
                        navController.navegaParaLoginDeslogado()
                    }
                },
                onUsuarioPesquisar = {
                    viewModel.pesquisaContato(it)
                }
            )
        }
    }
}

