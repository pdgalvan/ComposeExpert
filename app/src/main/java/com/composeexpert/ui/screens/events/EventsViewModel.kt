package com.composeexpert.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Event
import com.composeexpert.data.repositories.EventsRepository
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {
    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(isLoading = true)
            state = UIState(events = EventsRepository.get())
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val events: List<Event> = emptyList(),
    )
}