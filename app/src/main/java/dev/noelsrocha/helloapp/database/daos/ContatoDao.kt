package dev.noelsrocha.helloapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import dev.noelsrocha.helloapp.models.Contato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insere(contato: Contato)

    @Query("SELECT * FROM Contato")
    fun buscaTodos(): Flow<List<Contato>>

    @Query("SELECT * FROM Contato WHERE id = :id")
    fun buscaPorId(id: Long): Flow<Contato?>

    @Query("SELECT * FROM Contato WHERE nome LIKE :nome")
    fun buscaPorNome(nome: String): Flow<List<Contato>>

    @Query("SELECT * FROM Contato WHERE usuarioID = :usuarioID")
    fun buscaTodosPorUsuario(usuarioID: Int): Flow<List<Contato>>

    @Query("DELETE FROM Contato WHERE id = :id")
    suspend fun deleta(id: Long)
}