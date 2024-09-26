package com.to_get_ready.movies_app.di

import android.content.Context
import androidx.room.Room
import com.core.data.datasources.MoviesDataSource
import com.core.data.datasources.MoviesDataSourceImpl
import com.core.data.local.MoviesDao
import com.core.data.local.MoviesDatabase
import com.core.data.local.RemoteKeysDao
import com.core.data.remote.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
            .build()

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao = moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MoviesDatabase): RemoteKeysDao =
        moviesDatabase.getRemoteKeysDao()

    @Provides
    @Singleton
    fun provideMoviesDataSource(
        moviesDatabase: MoviesDatabase, moviesApiService: MoviesApiService,
    ): MoviesDataSource = MoviesDataSourceImpl(moviesDatabase, moviesApiService)
}