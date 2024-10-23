package com.composeexpert.ui.screens.comics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.composeexpert.data.entities.Comic
import com.composeexpert.data.network.entities.Result
import com.composeexpert.data.repositories.ComicsRepository
import com.composeexpert.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key)
        ?: throw IllegalArgumentException("comicId must be provided")

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { UIState(isLoading = true) }
            _state.update { UIState(comic = ComicsRepository.find(id)) }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val comic: Result<Comic?> = Either.Right(null),
    )
}