package com.to_get_ready.movies_app.di

import com.core.data.datasources.MoviesDataSource
import com.core.data.repos.MoviesRepoImpl
import com.core.domain.repos.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    @Provides
    @ViewModelScoped
    fun provideMoviesRepo(dataSource: MoviesDataSource): MoviesRepo = MoviesRepoImpl(dataSource)
}