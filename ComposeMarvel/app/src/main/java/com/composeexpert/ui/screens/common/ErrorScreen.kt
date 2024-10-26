package com.composeexpert.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.composeexpert.data.network.entities.Error

@Composable
fun ErrorScreen(error: Error) {
    val message = when (error) {
        Error.Connectivity -> "Connectivity error"
        is Error.Server -> "Server error: ${error.code}"
        is Error.Unknown -> "Unknown error: ${error.message}"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.headlineLarge.copy(
                textAlign = TextAlign.Center,
            )
        )
    }
}
