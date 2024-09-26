package com.to_get_ready.movies_app.di

import com.core.domain.repos.MoviesRepo
import com.core.domain.usecases.GetMovieDetailsUseCase
import com.core.domain.usecases.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetMoviesUseCase(moviesRepo: MoviesRepo): GetMoviesUseCase =
        GetMoviesUseCase(moviesRepo)
    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailsUseCase(moviesRepo: MoviesRepo): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(moviesRepo)
}