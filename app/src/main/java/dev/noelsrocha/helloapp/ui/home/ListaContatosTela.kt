package dev.noelsrocha.helloapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.noelsrocha.helloapp.R
import dev.noelsrocha.helloapp.models.Contato
import dev.noelsrocha.helloapp.sampleData.contatosExemplo
import dev.noelsrocha.helloapp.ui.components.AsyncImagePerfil
import dev.noelsrocha.helloapp.ui.components.LogoutDialog
import dev.noelsrocha.helloapp.ui.theme.HelloAppTheme

@Composable
fun ListaContatosTela(
    state: ListaContatosUiState,
    modifier: Modifier = Modifier,
    onClickDesloga: () -> Unit = {},
    onClickAbreDetalhes: (Long) -> Unit = {},
    onClickAbreCadastro: () -> Unit = {},
    onUsuarioPesquisar: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            AppBarListaContatos(
                state = state,
                onUsuarioPesquisar = onUsuarioPesquisar,
                onClickDesloga = {
                    state.onAbrirLogoutDialogMudou(true)
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { onClickAbreCadastro() },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.adicionar_novo_contato)
                )
            }
        }) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.contatos) { contato ->
                ContatoItem(contato) { idContato ->
                    onClickAbreDetalhes(idContato)
                }
            }
        }

        if (state.abrirLogoutDialog) {
            LogoutDialog(
                onDispensar = { state.onAbrirLogoutDialogMudou(false) },
                onDeslogar = {
                    state.onAbrirLogoutDialogMudou(false)
                    onClickDesloga()
                },
                nomeUsuario = state.nomeUsuario
            )
        }

        if (state.contatos.isEmpty()) {
            Box(modifier = Modifier.padding(top = 32.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "Nenhum contato encontrado"
                )
            }
        }
    }
}

@Composable
fun AppBarListaContatos(
    state: ListaContatosUiState,
    onUsuarioPesquisar: (String) -> Unit,
    onClickDesloga: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    label = { Text(stringResource(R.string.buscar_contato)) },
                    onValueChange = onUsuarioPesquisar,
                    value = state.pesquisaContato,
                    modifier = Modifier
                        .fillMaxSize(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Gray,
                        placeholderColor = Color.LightGray,
                        backgroundColor = Color.White
                    ),
                    placeholder = { Text(stringResource(R.string.buscar_contato)) },
                    shape =  RoundedCornerShape(100)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onClickDesloga
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.deslogar)
                )
            }
        }
    )
}

@Composable
fun ContatoItem(
    contato: Contato,
    onClick: (Long) -> Unit
) {
    Card(
        Modifier.clickable { onClick(contato.id) },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                urlImagem = contato.fotoPerfil,
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
                    text = contato.nome,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contato.sobrenome
                )
            }
        }
    }
}

@Preview
@Composable
fun ListaContatosPreview() {
    HelloAppTheme {
        ListaContatosTela(
            state = ListaContatosUiState(contatos = contatosExemplo)
        )
    }
}

@Preview
@Composable
fun ContatoItemPreview() {
    HelloAppTheme {
        ContatoItem(contatosExemplo.first()) {}
    }
}
