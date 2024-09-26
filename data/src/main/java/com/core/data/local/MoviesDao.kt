package com.core.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.data.entities.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("Select * From movies Order By page")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Query("Delete From movies")
    suspend fun clearAllMovies()

    @Query("Select * From movies Where id = :id")
    suspend fun getMovieById(id: Long): MovieEntity?
}