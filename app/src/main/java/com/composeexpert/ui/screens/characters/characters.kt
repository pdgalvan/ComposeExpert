package com.composeexpert.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composeexpert.data.entities.Character
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen

@Composable
fun CharactersScreen(
    onClick: (Character) -> Unit,
    charactersViewModel: CharactersViewModel = viewModel(),
) {
    MarvelItemsListScreen(
        items = charactersViewModel.state.characters,
        isLoading = charactersViewModel.state.isLoading,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel()) {

    MarvelItemDetailScreen(
        isLoading = viewModel.state.isLoading,
        marvelItem = viewModel.state.character,
    )
}