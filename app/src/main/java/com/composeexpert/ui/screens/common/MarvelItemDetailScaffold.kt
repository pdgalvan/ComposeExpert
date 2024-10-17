package com.composeexpert.ui.screens.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import com.composeexpert.data.entities.MarvelItem
import com.composeexpert.ui.navigation.AppBarIcon

@Composable
fun MarvelItemDetailScaffold(
    marvelItem: MarvelItem,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    AppBarIcon(imageVector = Icons.Default.Menu, onClick = {}, contentDescription = "Menu")
                    AppBarIcon(imageVector = Icons.Default.Favorite, onClick = {}, contentDescription = "Favorite")
                },
                floatingActionButton = {
                    if (marvelItem.urls.isNotEmpty()) {
                        FloatingActionButton(onClick = { shareMarvelItem(context, marvelItem) }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        },
        content = content
    )
}

private fun shareMarvelItem(context: Context, marvelItem: MarvelItem) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(marvelItem.title)
        .setText(marvelItem.urls.first().url)
        .intent
        .also(context::startActivity)
}