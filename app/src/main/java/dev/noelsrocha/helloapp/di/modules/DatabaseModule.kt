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
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideContatoDao(db: HelloAppDatabase): ContatoDao {
        return db.contatoDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HelloAppDatabase {
        return Room.databaseBuilder(
            context,
            HelloAppDatabase::class.java,
            "helloApp.db"
        ).build()
    }
}