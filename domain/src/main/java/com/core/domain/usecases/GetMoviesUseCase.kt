package com.core.domain.usecases

import androidx.paging.PagingData
import com.core.domain.models.Movie
import com.core.domain.models.MovieCategory
import com.core.domain.repos.MoviesRepo
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val moviesRepo: MoviesRepo) {
    operator fun invoke(movieCategory: MovieCategory): Flow<PagingData<Movie>> =
        moviesRepo.getMovies(movieCategory.category)
}