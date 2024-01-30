package dev.noelsrocha.helloapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.noelsrocha.helloapp.database.HelloAppDatabase
import dev.noelsrocha.helloapp.database.daos.ContatoDao
import dev.noelsrocha.helloapp.database.daos.UsuarioDao
import dev.noelsrocha.helloapp.database.migrations.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideContatoDao(db: HelloAppDatabase): ContatoDao {
        return db.contatoDao()
    }

    @Provides
    fun provideUsuarioDao(db: HelloAppDatabase): UsuarioDao {
        return db.usuarioDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HelloAppDatabase {
        return Room.databaseBuilder(context, HelloAppDatabase::class.java, "helloApp.db")
            .addMigrations(
                Migration202427011318,
                Migration202427011436,
                Migration202427012228,
                Migration202428011048,
                Migration202430010022
            )
            .build()
    }
}