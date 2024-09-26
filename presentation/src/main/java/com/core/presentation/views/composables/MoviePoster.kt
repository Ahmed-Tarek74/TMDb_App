package com.core.presentation.views.composables

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MoviePoster(
    posterUrl: Any,
    contentDescription: String,
    shape: Shape = RectangleShape,
    modifier: Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier,
        shape = shape
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillBounds
        )
    }
}
