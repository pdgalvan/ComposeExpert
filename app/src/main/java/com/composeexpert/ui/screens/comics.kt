package com.composeexpert.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.composeexpert.data.entities.Comic
import com.composeexpert.data.repositories.ComicsRepository
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }
    MarvelItemsListScreen(
        items = comicsState,
        onClick = onClick
    )
}

@Composable
fun ComicDetailScreen(comicId: Int, onBack: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(it, onBack)
    }
}