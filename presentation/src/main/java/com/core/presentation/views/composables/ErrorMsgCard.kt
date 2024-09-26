package com.core.presentation.views.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.presentation.theme.light_red
import com.core.presentation.theme.pink
import com.core.presentation.theme.red

@Composable
fun ErrorMsgCard(errorMsg: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.pink
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = (1.dp),
            color = colorScheme.light_red
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Text(
            text = errorMsg,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = colorScheme.red
            ),
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorMsgCardPreview() {
    ErrorMsgCard(errorMsg = "Failed to load now playing movies please, check your connection")
}
