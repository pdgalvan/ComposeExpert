package com.composeexpert.ui.screens.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Character
import com.composeexpert.data.repositories.CharactersRepository
import com.composeexpert.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key)
        ?: throw IllegalArgumentException("characterId must be provided")

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update { UIState(isLoading = true) }
            _state.update { UIState(character = CharactersRepository.find(id)) }
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val character: Character? = null,
    )
}