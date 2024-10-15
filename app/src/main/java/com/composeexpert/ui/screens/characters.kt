package com.composeexpert.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.composeexpert.data.repositories.CharactersRepository
import com.composeexpert.ui.screens.common.MarvelItemsListScreen
import com.composeexpert.data.entities.Character

import com.composeexpert.ui.screens.common.MarvelItemDetailScreen

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by remember{ mutableStateOf(emptyList<Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(characterId: Int, onBack: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }
    characterState?.let {
        MarvelItemDetailScreen(it, onBack)
    }
}