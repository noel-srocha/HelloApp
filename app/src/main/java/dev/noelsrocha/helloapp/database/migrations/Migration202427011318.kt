package dev.noelsrocha.helloapp.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration202427011318 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS Usuario (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `externalId` TEXT NOT NULL, `nome` TEXT NOT NULL, `senha` TEXT NOT NULL)")
    }
}