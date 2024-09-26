package com.core.data.datasources

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.core.data.entities.MovieEntity
import com.core.data.entities.RemoteKey
import com.core.data.local.MoviesDatabase
import com.core.data.mappers.toMovieRecords
import com.core.data.remote.MoviesApiService
import com.core.domain.exceptions.DataNotFoundException
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val moviesApiService: MoviesApiService,
) : MoviesDataSource {
    override fun getMoviesFromDb(): PagingSource<Int, MovieEntity> {
        return moviesDatabase.getMoviesDao().getMovies()
    }

    override suspend fun getMovieById(movieId: Long): MovieEntity {
        return moviesDatabase.getMoviesDao().getMovieById(movieId)
            ?: throw DataNotFoundException("Movie with ID $movieId not found in the local database")
    }

    override suspend fun loadPageOfMovies(page: Int, movieCategory: String): List<MovieEntity> {
        val movies =
            moviesApiService.getMoviesByCategory(
                page = page,
                movieCategory = movieCategory
            ).movies.toMovieRecords()
        return movies
    }

    override suspend fun saveMovies(
        movies: List<MovieEntity>,
        currentPage: Int,
        prevKey: Int?,
        nextKey: Int?,
    ) {
        moviesDatabase.withTransaction {
            val remoteKeys = movies.map {
                RemoteKey(
                    movieID = it.id,
                    prevKey = prevKey,
                    currentPage = currentPage,
                    nextKey = nextKey
                )
            }
            moviesDatabase.getRemoteKeysDao().insertAll(remoteKeys)
            moviesDatabase.getMoviesDao()
                .insertAll(movies.onEachIndexed { _, movie -> movie.page = currentPage })
        }
    }


    override suspend fun clearAllMovies() {
        moviesDatabase.withTransaction {
            moviesDatabase.getRemoteKeysDao().clearRemoteKeys()
            moviesDatabase.getMoviesDao().clearAllMovies()
        }
    }

    override suspend fun getRemoteKeyByMovieID(movieId: Long): RemoteKey? {
        return moviesDatabase.getRemoteKeysDao().getRemoteKeyByMovieID(movieId)
    }
}