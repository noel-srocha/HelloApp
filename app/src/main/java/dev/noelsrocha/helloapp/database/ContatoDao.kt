package dev.noelsrocha.helloapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.noelsrocha.helloapp.data.Contato

@Dao
interface ContatoDao {

    @Insert
    fun insere(contato: Contato)

    @Query("SELECT * FROM Contato")
    fun buscaTodos(): List<Contato>
}