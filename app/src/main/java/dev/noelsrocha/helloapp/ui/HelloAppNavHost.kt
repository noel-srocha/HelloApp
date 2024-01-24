package dev.noelsrocha.helloapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.noelsrocha.helloapp.DestinosHelloApp
import dev.noelsrocha.helloapp.DetalhesContato
import dev.noelsrocha.helloapp.FormularioContato
import dev.noelsrocha.helloapp.navigation.detalhesContatoGraph
import dev.noelsrocha.helloapp.navigation.formularioContatoGraph
import dev.noelsrocha.helloapp.navigation.homeGraph
import dev.noelsrocha.helloapp.navigation.loginGraph
import dev.noelsrocha.helloapp.navigation.splashGraph

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
        splashGraph(navController)
        loginGraph(navController)
        homeGraph(navController)
        formularioContatoGraph(navController)
        detalhesContatoGraph(navController)
    }
}


fun NavHostController.navegaDireto(rota: String) = this.navigate(rota) {
    popUpTo(this@navegaDireto.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navegaLimpo(rota: String) = this.navigate(rota) {
    popUpTo(0)
}

fun NavHostController.navegaParaDetalhes(idContato: Long) {
    navegaDireto("${DetalhesContato.rota}/$idContato")
}

fun NavHostController.navegaParaFormularioContato(idContato: Long = 0L) {
    navigate("${FormularioContato.rota}/$idContato")
}

fun NavHostController.navegaParaLoginDeslogado() {
    popBackStack(DestinosHelloApp.ListaContatos.rota, true)
    navegaDireto(DestinosHelloApp.LoginGraph.rota)
}