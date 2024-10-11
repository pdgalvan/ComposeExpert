package com.composeexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.composeexpert.ui.navigation.Navigation
import com.composeexpert.ui.theme.ComposeExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApp {
                Navigation()
            }
        }
    }
}

@Composable
fun MarvelApp(content: @Composable () -> Unit) {
    ComposeExpertTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}