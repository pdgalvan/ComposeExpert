package com.composeexpert.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composeexpert.data.entities.Character
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun CharactersScreen(
    onClick: (Character) -> Unit,
    viewModel: CharactersViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    MarvelItemsListScreen(
        items = state.characters,
        isLoading = state.isLoading,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    MarvelItemDetailScreen(
        isLoading = state.isLoading,
        marvelItem = state.character,
    )
}