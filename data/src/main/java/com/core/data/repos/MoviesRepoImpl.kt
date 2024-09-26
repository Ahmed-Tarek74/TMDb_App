package com.core.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.core.data.constants.Constants.PAGE_SIZE
import com.core.data.constants.Constants.PREFETCH_DISTANCE
import com.core.data.datasources.MoviesDataSource
import com.core.data.mappers.toMovie
import com.core.data.utils.MoviesRemoteMediator
import com.core.domain.exceptions.DataAccessException
import com.core.domain.exceptions.DataNotFoundException
import com.core.domain.exceptions.UnknownException
import com.core.domain.models.Movie
import com.core.domain.repos.MoviesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val dataSource: MoviesDataSource) : MoviesRepo {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(movieCategory: String): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { dataSource.getMoviesFromDb() }
        val pager = Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = PAGE_SIZE,
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MoviesRemoteMediator(
                moviesDataSource = dataSource,
                movieCategory = movieCategory
            )
        )
        return pager.flow.map { pagingData ->
            pagingData.map { movieEntity -> movieEntity.toMovie() }
        }
    }

    override suspend fun getMovieDetails(movieId: Long): Movie {
        return try {
            val movieEntity = dataSource.getMovieById(movieId)
            movieEntity.toMovie() // Convert entity to domain model
        } catch (e: DataNotFoundException) {
            throw e
        } catch (e: IOException) {
            throw DataAccessException(
                "Database access error occurred while fetching movie details.",
                e
            )
        } catch (e: Exception) {
            throw UnknownException(
                "An error occurred while fetching movie details: ${e.localizedMessage}",
                e
            )
        }
    }
}