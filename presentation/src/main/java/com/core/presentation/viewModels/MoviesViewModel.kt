package com.core.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.core.domain.models.MovieCategory
import com.core.domain.usecases.GetMoviesUseCase
import com.core.presentation.Intents.MoviesHomeIntent
import com.core.presentation.mappers.toUiModel
import com.core.presentation.models.MovieUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {
    private val _movies = MutableStateFlow<PagingData<MovieUIModel>>(PagingData.empty())
    val movies: StateFlow<PagingData<MovieUIModel>> get() = _movies

    private val _tabIndex = MutableStateFlow(0)
    val tabIndex: StateFlow<Int> get() = _tabIndex

    private val _intent = MutableSharedFlow<MoviesHomeIntent>()

    init {
        loadMoviesByCategory()
        processIntents()
    }

    fun setIntent(intent: MoviesHomeIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }

    private fun processIntents() {
        viewModelScope.launch {
            _intent.collectLatest { intent ->
                handleIntent(intent)
            }
        }
    }

    private fun handleIntent(intent: MoviesHomeIntent) {
        when (intent) {
            is MoviesHomeIntent.LoadMovies -> loadMoviesByCategory(intent.category)
            is MoviesHomeIntent.TabChanged -> updateTabIndex(intent.newTabIndex)
        }
    }

    private fun loadMoviesByCategory(movieCategory: MovieCategory = MovieCategory.NOW_PLAYING) {
        viewModelScope.launch {
            getMoviesUseCase(movieCategory).cachedIn(viewModelScope).map { pagingData ->
                pagingData.map { it.toUiModel() }
            }.collect {
                _movies.value = it
            }
        }
    }
    private fun updateTabIndex(newTabIndex: Int) {
        _tabIndex.value = newTabIndex
    }
}