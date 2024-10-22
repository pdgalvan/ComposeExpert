package com.composeexpert.ui.screens.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Comic
import com.composeexpert.data.repositories.ComicsRepository
import com.composeexpert.ui.navigation.NavArg
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key)
        ?: throw IllegalArgumentException("comicId must be provided")

    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(isLoading = true)
            state = UIState(comic = ComicsRepository.find(id))
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val comic: Comic? = null,
    )
}