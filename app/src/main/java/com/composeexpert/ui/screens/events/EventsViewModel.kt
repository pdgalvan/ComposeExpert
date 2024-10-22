package com.composeexpert.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Event
import com.composeexpert.data.repositories.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {
    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { UIState(isLoading = true) }
            _state.update { UIState(events = EventsRepository.get()) }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val events: List<Event> = emptyList(),
    )
}