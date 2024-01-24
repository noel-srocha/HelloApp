package dev.noelsrocha.helloapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.noelsrocha.helloapp.database.converters.Converters
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.models.Contato

@Database(entities = [Contato::class], version = 1)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}