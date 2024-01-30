package dev.noelsrocha.helloapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.noelsrocha.helloapp.models.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun buscarTodos(): Flow<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE nome = :nomeUsuario")
    fun buscarPorNome(nomeUsuario: String): Flow<Usuario?>

    @Query("SELECT * FROM Usuario WHERE id = :id")
    fun buscarPorId(id: Int): Flow<Usuario?>
}