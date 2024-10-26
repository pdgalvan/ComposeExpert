package com.composeexpert.ui.screens.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composeexpert.data.entities.Event
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun EventsScreen(
    onClick: (Event) -> Unit,
    viewModel: EventsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        items = state.events,
        isLoading = state.isLoading,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    MarvelItemDetailScreen(
        isLoading = state.isLoading,
        marvelItem = state.event,
    )
}

