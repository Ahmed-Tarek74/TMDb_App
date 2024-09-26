package com.core.domain.usecases

import com.core.domain.models.Movie
import com.core.domain.repos.MoviesRepo

class GetMovieDetailsUseCase(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieId: Long): Movie =
        moviesRepo.getMovieDetails(movieId)
}