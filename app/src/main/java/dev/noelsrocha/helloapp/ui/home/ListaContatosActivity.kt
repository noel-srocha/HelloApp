package dev.noelsrocha.helloapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dev.noelsrocha.helloapp.ui.HelloAppNavHost
import dev.noelsrocha.helloapp.ui.theme.HelloAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListaContatosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelloAppTheme {
                val navController = rememberNavController()
                HelloAppNavHost(
                    navController = navController,
                )
            }
        }
    }
}