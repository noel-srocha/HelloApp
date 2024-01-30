package dev.noelsrocha.helloapp.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(indices = [Index(value = ["nomeUsuario"], unique = true)])
data class Usuario(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val externalId: String = UUID.randomUUID().toString(),
    val nome: String = "",
    val nomeUsuario: String = "",
    val senha: String = ""
)