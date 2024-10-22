package com.composeexpert.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composeexpert.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    items: List<T>,
    isLoading: Boolean,
    onClick: (T) -> Unit
) {
    MarvelItemsList(
        items = items,
        isLoading = isLoading,
        onClick = onClick,
    )
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    items: List<T>,
    onClick: (T) -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }

        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
            ) {
                items(items) {
                    MarvelListItem(
                        item = it,
                        modifier = Modifier.clickable { onClick(it) }
                    )
                }
            }
        }
    }
}
