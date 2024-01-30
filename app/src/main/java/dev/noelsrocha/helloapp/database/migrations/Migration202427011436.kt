package dev.noelsrocha.helloapp.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration202427011436 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Usuario ADD COLUMN nomeUsuario TEXT NOT NULL DEFAULT ''")
    }
}