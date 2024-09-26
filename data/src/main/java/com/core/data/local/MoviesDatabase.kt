package com.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.data.entities.MovieEntity
import com.core.data.entities.RemoteKey

@Database(
    entities = [MovieEntity::class, RemoteKey::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}