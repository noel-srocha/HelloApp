package dev.noelsrocha.helloapp.ui.userDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.noelsrocha.helloapp.R
import dev.noelsrocha.helloapp.models.Usuario
import dev.noelsrocha.helloapp.ui.components.AsyncImagePerfil
import dev.noelsrocha.helloapp.ui.theme.HelloAppTheme

@Composable
fun GerenciaUsuariosTela(
    state: GerenciaUsuariosUIState,
    modifier: Modifier = Modifier,
    onClickAbreDetalhes: (Int) -> Unit = {},
    onClickVolta: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBarGerenciaUsuarios(
                onClickVolta = onClickVolta
            )
        }
    ) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.usuarios) { usuario ->
                UsuarioGerenciaItem(usuario) { idUsuario ->
                    onClickAbreDetalhes(idUsuario)
                }
            }
        }

    }
}

@Composable
fun AppBarGerenciaUsuarios(
    onClickVolta: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.gerenciar_usuarios))
        },
        navigationIcon = {
            IconButton(
                onClick = onClickVolta
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }
        })
}

@Composable
fun UsuarioGerenciaItem(
    usuario: Usuario,
    onClick: (Int) -> Unit
) {
    Card(
        Modifier.clickable {
            onClick(usuario.id)
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = usuario.nome,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = usuario.nomeUsuario
                )
            }
        }
    }
}


@Preview
@Composable
fun AppBarGerenciaUsuariosPreview() {
    HelloAppTheme {
        AppBarGerenciaUsuarios()
    }
}

@Preview
@Composable
fun BuscaContatosPreview() {
    HelloAppTheme {
        GerenciaUsuariosTela(
            state = GerenciaUsuariosUIState()
        )
    }
}