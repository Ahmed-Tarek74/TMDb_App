package com.core.presentation.views.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.presentation.R
import com.core.presentation.theme.MoviesAppTheme
import com.core.presentation.theme.red
import com.core.presentation.theme.white

@Composable
fun ErrorDialog(
    errorMsg: String,
    confirmBtnActionMsg: String = stringResource(id = R.string.retry),
    dismissBtnActionMsg: String = stringResource(id = R.string.dismiss),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        shape = RoundedCornerShape(20.dp),
        containerColor = colorScheme.white,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.error),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = colorScheme.red
                )
            )
        },
        text = {
            Text(
                text = errorMsg,
                style = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.red)
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = dismissBtnActionMsg,
                    style = MaterialTheme.typography.headlineMedium.copy(color = colorScheme.red)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = confirmBtnActionMsg,
                    style = MaterialTheme.typography.headlineMedium.copy(color = colorScheme.red)
                )
            }
        }, tonalElevation = 4.dp,
        modifier = Modifier.border(
            width = 2.dp,
            color = colorScheme.red,
            shape = RoundedCornerShape(20.dp)
        )
    )
}

@Composable
@Preview(showBackground = true)
fun ErrorDialogPreview() {
    MoviesAppTheme {
        ErrorDialog(
            errorMsg = "Failed to load now playing movies please, check your connection ",
            onConfirm = {}
        ) {}
    }
}
