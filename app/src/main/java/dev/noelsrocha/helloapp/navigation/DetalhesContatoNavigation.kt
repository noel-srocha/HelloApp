package dev.noelsrocha.helloapp.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.noelsrocha.helloapp.DetalhesContato
import dev.noelsrocha.helloapp.ui.details.DetalhesContatoTela
import dev.noelsrocha.helloapp.ui.details.DetalhesContatoViewlModel
import dev.noelsrocha.helloapp.ui.navegaParaFormularioContato
import dev.noelsrocha.helloapp.util.ID_CONTATO
import kotlinx.coroutines.launch

fun NavGraphBuilder.detalhesContatoGraph(
    navController: NavHostController
) {
    composable(
        route = DetalhesContato.rotaComArgumentos,
        arguments = DetalhesContato.argumentos
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTATO
        )?.let { idContato ->

            val viewModel = hiltViewModel<DetalhesContatoViewlModel>()
            val state by viewModel.uiState.collectAsState()

            val scope = rememberCoroutineScope()

            DetalhesContatoTela(
                state = state,
                onClickVoltar = { navController.popBackStack() },
                onApagaContato = {
                    scope.launch {
                        viewModel.removeContato()
                    }
                    navController.popBackStack()
                },
                onClickEditar = { navController.navegaParaFormularioContato(idContato) })
        }
    }
}