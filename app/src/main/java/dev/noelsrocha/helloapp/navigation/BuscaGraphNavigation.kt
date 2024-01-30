package dev.noelsrocha.helloapp.navigation

import dev.noelsrocha.helloapp.DestinosHelloApp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.noelsrocha.helloapp.ui.search.BuscaContatosTela
import dev.noelsrocha.helloapp.ui.search.BuscaContatosViewModel

fun NavGraphBuilder.buscaContatosGraph(
    onVolta: () -> Unit,
    onClickNavegaParaDetalhesContato: (Long) -> Unit
) {
    composable(route = DestinosHelloApp.BuscaContatos.rota) {
        val viewModel = hiltViewModel<BuscaContatosViewModel>()
        val state by viewModel.uiState.collectAsState()

        BuscaContatosTela(
            state = state,
            onClickVoltar = onVolta,
            onClickAbrirDetalhes = { idContato ->
                onClickNavegaParaDetalhesContato(idContato)
            }
        )
    }
}