package dev.noelsrocha.helloapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.ui.home.ListaContatosTela
import dev.noelsrocha.helloapp.ui.home.ListaContatosViewModel

fun NavGraphBuilder.homeGraph(
    onNavegarParaDetalhes: (Long) -> Unit,
    onNavegarParaFormularioContato: () -> Unit,
        onNavegarParaDialogUsuarios: (Int) -> Unit,
    onNavegarParaBuscaContatos: () -> Unit
) {
    navigation(
        startDestination = DestinosHelloApp.ListaContatos.rota,
        route = DestinosHelloApp.HomeGraph.rota,
    ) {
        composable(route = DestinosHelloApp.ListaContatos.rota) {
            val viewModel = hiltViewModel<ListaContatosViewModel>()
            val state by viewModel.uiState.collectAsState()

            ListaContatosTela(
                state = state,
                onClickAbreDetalhes = { idContato -> onNavegarParaDetalhes(idContato) },
                onClickAbreCadastro = onNavegarParaFormularioContato,
                onClickListarUsuarios = {
                    state.usuarioID?.let {
                        onNavegarParaDialogUsuarios(it)
                    }
                },
                onClickBuscarContatos = onNavegarParaBuscaContatos,
            )
        }
    }
}

