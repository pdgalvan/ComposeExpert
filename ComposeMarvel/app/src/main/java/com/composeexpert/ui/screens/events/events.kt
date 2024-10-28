package com.composeexpert.ui.screens.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeexpert.data.entities.Event
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun EventsScreen(
    onClick: (Event) -> Unit,
    viewModel: EventsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        items = state.events,
        isLoading = state.isLoading,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    MarvelItemDetailScreen(
        isLoading = state.isLoading,
        marvelItem = state.event,
    )
}

