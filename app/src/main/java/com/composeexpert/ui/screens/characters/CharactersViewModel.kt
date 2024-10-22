package com.composeexpert.ui.screens.characters

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composeexpert.data.entities.Character
import com.composeexpert.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(isLoading = true)
            state = UIState(characters = CharactersRepository.get())
        }
    }

    data class UIState(
        val isLoading: Boolean = false,
        val characters: List<Character> = emptyList(),
    )
}
