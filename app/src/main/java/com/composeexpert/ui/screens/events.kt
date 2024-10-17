package com.composeexpert.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.composeexpert.data.entities.Event
import com.composeexpert.data.repositories.EventsRepository
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by remember { mutableStateOf(emptyList<Event>()) }
    LaunchedEffect(Unit) {
        eventsState = EventsRepository.get()
    }
    MarvelItemsListScreen(
        items = eventsState,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(eventId: Int) {
    var eventState by remember { mutableStateOf<Event?>(null) }
    LaunchedEffect(Unit) {
        eventState = EventsRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(it)
    }
}

