package com.core.presentation.views.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MovieItemCard(movie: MovieUIModel, onClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 48.dp)
            .fillMaxWidth()
            .height(270.dp)
            .clickable { onClick(movie.movieId) },
        border = BorderStroke(
            width = 1.dp,
            color = colorScheme.black
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.white
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MoviePoster(
                posterUrl = movie.posterPath,
                contentDescription = stringResource(R.string.movie_poster_description, movie.title),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.5f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .background(color = colorScheme.black)
                    .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 16.dp)
                    .fillMaxWidth()
            ) {
                MovieItemContent(
                    movieTitle = movie.title,
                    releaseDate = movie.releaseDate,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                MovieRatingCard(
                    ratingPercentage = movie.ratingPercentage,
                    rating = movie.rating,
                    progressColor = movie.progressColor,
                )
            }
        }
    }
}

@Composable
private fun MovieItemContent(
    movieTitle: String, releaseDate: String, modifier: Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = movieTitle,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = releaseDate,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.SEMI_BOLD
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieItemCardPreview() {
    MoviesAppTheme {
        MovieItemCard(
            MovieUIModel(
                movieId = 12345,
                title = "Borderlands",
                overview =
                "Returning to her home planet, an infamous bounty hunter forms an unexpected alliance with a team of unlikely heroes. Together, they battle monsters and dangerous bandits to protect a young girl who holds the key to unimaginable power.",
                _posterPath = null,
                releaseDate = "07-08-2024",
                rating = 0.744f,
                _ratingPercentage = 74
            )
        ) {}
    }
}