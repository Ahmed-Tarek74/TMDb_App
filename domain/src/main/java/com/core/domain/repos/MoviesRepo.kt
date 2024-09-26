package com.core.domain.repos

import androidx.paging.PagingData
import com.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {
    fun getMovies(movieCategory: String): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Long): Movie
}