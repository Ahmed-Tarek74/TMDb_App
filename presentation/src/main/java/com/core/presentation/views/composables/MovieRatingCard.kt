package com.core.presentation.views.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.presentation.theme.MoviesAppTheme
import com.core.presentation.theme.primary_dark_blue
import com.core.presentation.theme.white

@Composable
fun MovieRatingCard(
    ratingPercentage: String,
    rating: Float, // Current rating value
    progressColor: Color = colorScheme.primary_dark_blue, // Color of the progress (rating)
    strokeWidth: Float = 6f,// Thickness of the circle
    shadowAlpha: Float = 0.65f,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .border(shape = CircleShape, width = 1.dp, color = colorScheme.white),

    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // Background circular indicator (full circle)
        // Internal (background) circle
        CircularProgressIndicator(
            progress = {
                1f // Full circle for the background
            },
            color = progressColor,
            strokeWidth = strokeWidth.dp,
            modifier = Modifier.alpha(shadowAlpha)
        )
        // Foreground circular indicator (rating progress)
        CircularProgressIndicator(
            progress = {
                rating // Progress based on the rating
            },
            color = progressColor,
            strokeWidth = strokeWidth.dp,
            trackColor = Color.Transparent,
        )

        // Rating text in the center
        Text(
            text = ratingPercentage,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
@Preview(showBackground = false)
fun MovieRatingCardPreview() {
    MoviesAppTheme {
        MovieRatingCard(ratingPercentage = "20%", rating = 0.2f)
    }
}
