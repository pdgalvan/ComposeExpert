package com.composeexpert.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Event
import com.composeexpert.data.repositories.EventsRepository
import com.composeexpert.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key)
        ?: throw IllegalArgumentException("eventId must be provided")

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {UIState(isLoading = true) }
            _state.update { UIState(event = EventsRepository.find(id)) }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val event: Event? = null,
    )
}