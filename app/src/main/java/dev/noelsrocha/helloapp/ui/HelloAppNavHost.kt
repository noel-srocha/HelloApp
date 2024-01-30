package dev.noelsrocha.helloapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.DetalhesContato
import dev.noelsrocha.helloapp.FormularioContato
import dev.noelsrocha.helloapp.FormularioUsuario
import dev.noelsrocha.helloapp.ListaUsuarios
import dev.noelsrocha.helloapp.navigation.buscaContatosGraph
import dev.noelsrocha.helloapp.navigation.detalhesContatoGraph
import dev.noelsrocha.helloapp.navigation.formularioContatoGraph
import dev.noelsrocha.helloapp.navigation.homeGraph
import dev.noelsrocha.helloapp.navigation.loginGraph
import dev.noelsrocha.helloapp.navigation.splashGraph
import dev.noelsrocha.helloapp.navigation.usuariosGraph

@Composable
fun HelloAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinosHelloApp.SplashScreen.rota,
        modifier = modifier
    ) {
        splashGraph(
            onNavegarParaLogin = {
                navController.navegarParaLoginElimpaBackStack()
            },
            onNavegarParaHome = {
                navController.navegarParaHome()
            },
        )
        loginGraph(
            onNavegarParaHome = {
                navController.navegarParaHome()
            },
            onNavegarParaFormularioLogin = {
                navController.navegarParaFormlarioLogin()
            },
            onNavegarParaLogin = {
                navController.navegarParaLoginElimpaBackStack()
            },
        )
        homeGraph(
            onNavegarParaDetalhes = { idContato ->
                navController.navegarParaDetalhes(idContato)
            },
            onNavegarParaFormularioContato = {
                navController.navegarParaFormularioContato()
            },
            onNavegarParaDialogUsuarios = { idUsuario ->
                navController.navegarParaDialogUsuarios(idUsuario)
            },
            onNavegarParaBuscaContatos = {
                navController.navegarParaBuscaContatos()
            }
        )
        formularioContatoGraph(
            onVoltar = {
                navController.popBackStack()
            },
        )
        detalhesContatoGraph(
            onVoltar = {
                navController.popBackStack()
            },
            onNavegarParaDialogUsuarios = { idContato ->
                navController.navegarParaFormularioContato(idContato)
            },
        )
        usuariosGraph(
            onVoltar = {
                navController.popBackStack()
            },
            onNavegarParaLogin = {
                navController.navegarParaLogin()
            },
            onNavegarParaHome = {
                navController.navegarParaHome()
            },
            onNavegarGerenciarUsuarios = {
                navController.navegarParaGerenciaUsuarios()
            },
            onNavegarParaFormularioUsuario = { idUsuario ->
                navController.navegarParaFormularioUsuario(idUsuario)
            },
        )
        buscaContatosGraph(
            onVolta = {
                navController.popBackStack()
            },
            onClickNavegaParaDetalhesContato = { idContato ->
                navController.navegarParaDetalhes(idContato)
            },
        )
    }
}


fun NavHostController.navegarDireto(rota: String) = this.navigate(rota) {
    popUpTo(this@navegarDireto.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navegarLimpo(rota: String) = this.navigate(rota) {
    popUpTo(0)
}

fun NavHostController.navegarParaDetalhes(idContato: Long) {
    navegarDireto("${DetalhesContato.rota}/$idContato")
}

fun NavHostController.navegarParaFormularioContato(idContato: Long = 0L) {
    navigate("${FormularioContato.rota}/$idContato")
}

fun NavHostController.navegarParaLoginElimpaBackStack() {
    navegarLimpo(DestinosHelloApp.LoginGraph.rota)
}

fun NavHostController.navegarParaDialogUsuarios(idUsuario: Int) {
    navigate("${ListaUsuarios.rota}/$idUsuario")
}

fun NavHostController.navegarParaFormularioUsuario(idUsuario: Int) {
    navigate("${FormularioUsuario.rota}/$idUsuario")
}

fun NavHostController.navegarParaLogin() {
    navigate(DestinosHelloApp.Login.rota)
}

fun NavHostController.navegarParaHome() {
    navegarLimpo(DestinosHelloApp.HomeGraph.rota)
}

fun NavHostController.navegarParaFormlarioLogin() {
    navigate(DestinosHelloApp.FormularioLogin.rota)
}

fun NavHostController.navegarParaGerenciaUsuarios() {
    navigate(DestinosHelloApp.GerenciaUsuarios.rota)
}

fun NavHostController.navegarParaBuscaContatos() {
    navigate(DestinosHelloApp.BuscaContatos.rota)
}