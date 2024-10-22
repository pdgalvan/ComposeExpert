package com.composeexpert.ui.screens.events

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composeexpert.data.entities.Event
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun EventsScreen(
    onClick: (Event) -> Unit,
    viewModel: EventsViewModel = viewModel()
) {
    MarvelItemsListScreen(
        items = viewModel.state.events,
        isLoading = viewModel.state.isLoading,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(
        isLoading = viewModel.state.isLoading,
        marvelItem = viewModel.state.event,
    )
}

