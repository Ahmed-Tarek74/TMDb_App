package com.core.data.datasources

import androidx.paging.PagingSource
import com.core.data.entities.MovieEntity
import com.core.data.entities.RemoteKey
import com.core.domain.models.MovieCategory

interface MoviesDataSource {
    fun getMoviesFromDb(): PagingSource<Int, MovieEntity>
    suspend fun getMovieById(movieId: Long): MovieEntity
    suspend fun loadPageOfMovies(page: Int, movieCategory: String): List<MovieEntity>
    suspend fun saveMovies(
        movies: List<MovieEntity>,
        currentPage: Int,
        prevKey: Int?,
        nextKey: Int?,
    )

    suspend fun clearAllMovies()
    suspend fun getRemoteKeyByMovieID(movieId: Long): RemoteKey?

}
