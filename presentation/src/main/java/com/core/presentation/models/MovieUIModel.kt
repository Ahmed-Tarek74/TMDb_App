package com.core.presentation.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.core.presentation.R
import com.core.presentation.theme.green
import com.core.presentation.theme.red
import com.core.presentation.theme.yellow

data class MovieUIModel(
    val movieId:Long,
    val title: String,
    val overview: String,
    private val _posterPath: String?,
    val releaseDate: String,
    val rating: Float,
    private val _ratingPercentage: Int,
) {
    val posterPath: Any
        get() = _posterPath ?: R.drawable.movie_default_poster

    val ratingPercentage: String
        get() = "${_ratingPercentage}%"

    val progressColor: Color
        @Composable
        get() = when {
            _ratingPercentage >= 70 -> MaterialTheme.colorScheme.green
            _ratingPercentage < 50 -> MaterialTheme.colorScheme.red
            else -> MaterialTheme.colorScheme.yellow
        }
}