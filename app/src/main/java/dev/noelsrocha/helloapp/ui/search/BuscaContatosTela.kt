package dev.noelsrocha.helloapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.noelsrocha.helloapp.R
import dev.noelsrocha.helloapp.sampleData.contatosExemplo
import dev.noelsrocha.helloapp.ui.home.ContatoItem
import dev.noelsrocha.helloapp.ui.theme.HelloAppTheme

@Composable
fun BuscaContatosTela(
    state: BuscaContatosUIState,
    modifier: Modifier = Modifier,
    onClickAbrirDetalhes: (Long) -> Unit = {},
    onClickVoltar: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBarBuscaContatos(
                onValorMudou = {
                    state.onValorBuscaMudou(it)
                },
                onClickVolta = onClickVoltar,
                valor = state.valorBusca
            )
        },
    ) { paddingValues ->

        LazyColumn(Modifier.padding(paddingValues)) {
            items(state.contatos) { contato ->
                ContatoItem(contato) { idContato ->
                    onClickAbrirDetalhes(idContato)
                }
            }
        }
    }
}

@Composable
fun AppBarBuscaContatos(
    valor: String,
    onValorMudou: (String) -> Unit = {},
    onClickVolta: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClickVolta
            ) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = stringResource(R.string.voltar)
                )
            }

            BasicTextField(
                value = valor,
                onValueChange = onValorMudou,
                decorationBox = { valorInterno ->
                    Box(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        if (valor.isEmpty()) {
                            Text(stringResource(R.string.pesquisar_contatos), color = Color.Gray)
                        }
                        valorInterno()
                    }
                },
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
            )
        }
        Divider(thickness = 1.dp)
    }
}


@Preview
@Composable
fun AppBarBuscaContatosPreview() {
    HelloAppTheme {
        AppBarBuscaContatos("")
    }
}

@Preview
@Composable
fun BuscaContatosPreview() {
    HelloAppTheme {
        BuscaContatosTela(
            state = BuscaContatosUIState(
                contatos = contatosExemplo,
                usuarioAtual = ""
            )
        )
    }
}