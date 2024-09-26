package com.core.presentation.viewState

import com.core.presentation.models.MovieUIModel

sealed class MovieDetailsViewState {
    data object Loading : MovieDetailsViewState()
    data class Error(val errorMsg: String) : MovieDetailsViewState()
    data class Success(val movie: MovieUIModel) : MovieDetailsViewState()
}