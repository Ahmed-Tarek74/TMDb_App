package com.core.presentation.Intents

import com.core.domain.models.MovieCategory

sealed class MoviesHomeIntent {
    data class LoadMovies(val category: MovieCategory) : MoviesHomeIntent()
    data class TabChanged(val newTabIndex: Int) : MoviesHomeIntent()
}