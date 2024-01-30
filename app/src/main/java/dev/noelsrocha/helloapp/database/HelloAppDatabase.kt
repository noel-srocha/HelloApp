package dev.noelsrocha.helloapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.noelsrocha.helloapp.database.converters.Converters
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import dev.noelsrocha.helloapp.models.Contato
import dev.noelsrocha.helloapp.models.Usuario

@Database(
    entities = [Contato::class, Usuario::class],
    version = 6,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    abstract fun usuarioDao(): UsuarioDao
}