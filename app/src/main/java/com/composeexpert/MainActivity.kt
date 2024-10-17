package com.composeexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.composeexpert.ui.MarvelApp
import com.composeexpert.ui.MarvelScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelScreen {
                MarvelApp()
            }
        }
    }
}
