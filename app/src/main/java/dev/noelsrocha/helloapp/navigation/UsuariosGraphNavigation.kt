package dev.noelsrocha.helloapp.navigation

import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.FormularioUsuario
import dev.noelsrocha.helloapp.ListaUsuarios
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import dev.noelsrocha.helloapp.ui.userDialog.*
import dev.noelsrocha.helloapp.util.ID_USUARIO_ATUAL
import kotlinx.coroutines.launch

fun NavGraphBuilder.usuariosGraph(
    onVoltar: () -> Unit,
    onNavegarParaLogin: () -> Unit,
    onNavegarParaHome: () -> Unit,
    onNavegarGerenciarUsuarios: () -> Unit,
    onNavegarParaFormularioUsuario: (Int) -> Unit,
) {
    navigation(
        startDestination = ListaUsuarios.rota,
        route = DestinosHelloApp.UsuariosGraph.rota
    ) {
        dialog(
            route = ListaUsuarios.rotaComArgumentos,
            arguments = ListaUsuarios.argumentos
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getLong(ID_USUARIO_ATUAL)?.let { usuarioAtual ->

                val viewModel = hiltViewModel<ListaUsuariosViewModel>()
                val state by viewModel.uiState.collectAsState()

                val coroutineScope = rememberCoroutineScope()

                CaixaDialogoContasUsuario(
                    state = state,
                    onClickDispensa = onVoltar,
                    onClickAdicionaNovaConta = {
                        onNavegarParaLogin()
                    },
                    onClickListaContatosPorUsuario = { novoUsuario ->
                        coroutineScope.launch {
                            viewModel.atualizarUsuarioLogado(novoUsuario)
                            onNavegarParaHome()
                        }
                    },
                    onClickGerenciaUsuarios = {
                        onNavegarGerenciarUsuarios()
                    }
                )
            }
        }

        composable(
            route = DestinosHelloApp.GerenciaUsuarios.rota
        ) {
            val viewModel = hiltViewModel<GerenciaUsuariosViewModel>()
            val state by viewModel.uiState.collectAsState()

            GerenciaUsuariosTela(
                state = state,
                onClickAbreDetalhes = { usuarioAtual ->
                    onNavegarParaFormularioUsuario(usuarioAtual)
                },
                onClickVolta = onVoltar
            )
        }

        composable(
            route = FormularioUsuario.rotaComArgumentos,
            arguments = FormularioUsuario.argumentos
        ) { usuarioAtual ->
            val viewModel = hiltViewModel<FormularioUsuarioViewModel>()
            val state by viewModel.uiState.collectAsState()
            val coroutineScope = rememberCoroutineScope()

            FormularioUsuarioTela(
                state = state,
                onClickVolta = onVoltar,
                onClickSalva = {
                    coroutineScope.launch {
                        onVoltar()
                    }
                },
                onClickApaga = {
                    coroutineScope.launch {
                        onVoltar()
                    }
                },
                onClickMostraMensagemExclusao = {
                    viewModel.onClickMostraMensagemExclusao()
                }
            )
        }
    }
}