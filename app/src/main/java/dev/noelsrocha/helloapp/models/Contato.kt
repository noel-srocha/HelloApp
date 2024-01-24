package dev.noelsrocha.helloapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.noelsrocha.helloapp.ui.form.FormularioContatoUiState
import java.util.*

@Entity
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String = "",
    val sobrenome: String = "",
    val telefone: String = "",
    val fotoPerfil: String = "",
    val aniversario: Date? = null,
) {
    companion object {
        fun fromFormulario(state: FormularioContatoUiState): Contato {
            return Contato(
                id = state.id,
                nome = state.nome,
                sobrenome = state.sobrenome,
                telefone = state.telefone,
                fotoPerfil = state.fotoPerfil,
                aniversario = state.aniversario
            )
        }
    }
}