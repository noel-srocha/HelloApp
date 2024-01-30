package dev.noelsrocha.helloapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.noelsrocha.helloapp.FormularioContato
import dev.noelsrocha.helloapp.R
import dev.noelsrocha.helloapp.ui.form.FormularioContatoTela
import dev.noelsrocha.helloapp.ui.form.FormularioContatoViewModel
import dev.noelsrocha.helloapp.util.ID_CONTATO
import kotlinx.coroutines.launch

fun NavGraphBuilder.formularioContatoGraph(
    onVoltar: () -> Unit,
) {
    composable(
        route = FormularioContato.rotaComArgumentos,
        arguments = FormularioContato.argumentos
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getLong(
            ID_CONTATO
        )?.let {

            val viewModel = hiltViewModel<FormularioContatoViewModel>()
            val state by viewModel.uiState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(state.aniversario) {
                viewModel.defineTextoAniversario(
                    context.getString(R.string.aniversario)
                )
            }

            val coroutineScope = rememberCoroutineScope()

            FormularioContatoTela(
                state = state,
                onClickSalvar = {
                    coroutineScope.launch{
                        viewModel.salvar()
                    }
                    onVoltar()
                },
                onCarregarImagem = {
                    viewModel.carregarImagem(it)
                }
            )
        }
    }
}