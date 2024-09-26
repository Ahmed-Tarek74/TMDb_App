package com.core.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.core.domain.exceptions.DataAccessException
import com.core.domain.usecases.GetMovieDetailsUseCase
import com.core.presentation.Route.*
import com.core.presentation.mappers.toUiModel
import com.core.presentation.viewState.MovieDetailsViewState
import com.core.presentation.viewState.MovieDetailsViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    private val movieID = savedStateHandle.toRoute<MovieDetailsScreen>().movieId
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }
    private val _viewState: MutableStateFlow<MovieDetailsViewState> =
        MutableStateFlow(Loading)
    val viewState: StateFlow<MovieDetailsViewState> = _viewState

    init {
        getMovieDetails(movieID)
    }

    private fun handleException(exception: Throwable) {
        if (exception is DataAccessException) {
            _viewState.value = Error(
                exception.message ?: "Database access error occurred while fetching movie details"
            )
        }
    }
    private fun getMovieDetails(movieId: Long) {
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                _viewState.value = Success(getMovieDetailsUseCase(movieId).toUiModel())
            } catch (e: Exception) {
                _viewState.value = Error(e.message ?: "Failed to load movie")
            }
        }
    }
}