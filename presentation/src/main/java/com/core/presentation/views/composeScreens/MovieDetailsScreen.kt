package com.core.presentation.views.composeScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.presentation.R
import com.core.presentation.models.MovieUIModel
import com.core.presentation.theme.MoviesAppTheme
import com.core.presentation.theme.SEMI_BOLD
import com.core.presentation.theme.black
import com.core.presentation.theme.white
import com.core.presentation.viewState.MovieDetailsViewState
import com.core.presentation.viewState.MovieDetailsViewState.*
import com.core.presentation.views.composables.ErrorMsgCard
import com.core.presentation.views.composables.LoadingDialog
import com.core.presentation.views.composables.MoviePoster
import com.core.presentation.views.composables.MovieRatingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(viewState: State<MovieDetailsViewState>, navigateBack: () -> Unit) {
    when (viewState.value) {
        is Loading -> LoadingDialog(stringResource(id = R.string.loading)) {}
        is Error -> {
            ErrorMsgCard(errorMsg = (viewState.value as Error).errorMsg)
        }
        is Success -> {
            val movie = (viewState.value as Success).movie
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarColors(
                            containerColor = colorScheme.black,
                            scrolledContainerColor = colorScheme.white,
                            navigationIconContentColor = colorScheme.white,
                            titleContentColor = colorScheme.white,
                            actionIconContentColor = colorScheme.white
                        ),
                        title = {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(4.dp)
                            )
                        }, navigationIcon = {
                            IconButton(onClick = navigateBack) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(id = R.string.back),
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                )

                            }
                        })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .background(color = colorScheme.white)
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MoviePoster(
                        posterUrl = movie.posterPath,
                        contentDescription = stringResource(
                            R.string.movie_poster_description,
                            movie.title
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .weight(1.5f)
                    )
                    MovieDetailsContent(
                        movie = movie, modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .weight(2f)
                    )
                }
            }
        }
    }

}

@Composable
private fun MovieDetailsContent(movie: MovieUIModel, modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.black),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = (1.dp), color = colorScheme.black),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.overview),
                    style = MaterialTheme.typography.headlineMedium
                )
                MovieRatingCard(
                    ratingPercentage = movie.ratingPercentage,
                    rating = movie.rating,
                    progressColor = movie.progressColor,
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = FontFamily.SEMI_BOLD
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieComposableScreenPreview() {
    MoviesAppTheme {
        MovieDetailsScreen(
            remember {
                mutableStateOf(
                    Success(
                        MovieUIModel(
                            movieId = 12345,
                            title = "Borderlands",
                            overview =
                            "Returning to her home planet, an infamous bounty hunter forms an unexpected alliance with a team of unlikely heroes. Together, they battle monsters and dangerous bandits to protect a young girl who holds the key to unimaginable power.",
                            _posterPath = null,
                            releaseDate = "07-08-2024",
                            rating = 0.244f,
                            _ratingPercentage = 24
                        )
                    )
                )
            }
        ) {}
    }
}

