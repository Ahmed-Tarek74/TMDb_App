package com.core.presentation.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.presentation.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.core.presentation.models.MovieUIModel
import com.core.presentation.theme.black
import com.core.presentation.theme.white


@Composable
fun MoviesListBox(
    movies: LazyPagingItems<MovieUIModel>,
    onMovieSelected: (Long) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.white)
    ) {
        if (movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                color = colorScheme.black,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            if (movies.itemCount == 0) {
                EmptyScreen()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(movies.itemCount) { index ->
                        val item = movies[index]
                        item?.let {
                            MovieItemCard(movie = it, onMovieSelected)
                        }
                    }
                    item {
                        if (movies.loadState.refresh is LoadState.Error) {
                            val errorMsg =
                                (movies.loadState.refresh as LoadState.Error).error.message
                            ErrorMsgCard(errorMsg = errorMsg!!)
                        }
                        when (val loadState = movies.loadState.append) {
                            is LoadState.Loading -> {
                                CircularProgressIndicator(color = colorScheme.black)
                            }

                            is LoadState.Error -> {
                                val errorMsg = loadState.error.message
                                ErrorMsgCard(errorMsg = errorMsg!!)
                            }

                            is LoadState.NotLoading -> Unit
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_movies_available),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}