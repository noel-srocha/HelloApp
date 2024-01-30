package dev.noelsrocha.helloapp.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration202430010022 : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS ContatoCopia (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `sobrenome` TEXT NOT NULL, `telefone` TEXT NOT NULL, `fotoPerfil` TEXT NOT NULL, `aniversario` INTEGER, `usuarioID` INTEGER NOT NULL DEFAULT 0, FOREIGN KEY(`usuarioID`) REFERENCES `Usuario`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        database.execSQL("INSERT INTO ContatoCopia SELECT * FROM Contato")
        database.execSQL("DROP TABLE Contato")
        database.execSQL("ALTER TABLE ContatoCopia RENAME TO Contato")
    }
}