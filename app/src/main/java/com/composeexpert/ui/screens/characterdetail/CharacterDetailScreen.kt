package com.composeexpert.ui.screens.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.composeexpert.MarvelApp
import com.composeexpert.data.entities.Character
import com.composeexpert.data.entities.Reference
import com.composeexpert.data.repositories.CharactersRepository

@Composable
fun CharacterDetailScreen(id: Int) {
    var character by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        character = CharactersRepository.finCharacter(id)
    }
    character?.let {
        CharacterDetailScreen(it)
    }
}

@Composable
fun CharacterDetailScreen(character: Character) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()

    ) {
        item {
            Header(character)
        }
        section(Icons.Default.Collections, "Series", character.series)
        section(Icons.Default.Event, "Events", character.events)
        section(Icons.Default.Book, "Comics", character.comics)
        section(Icons.Default.Bookmark, "Stories", character.stories)
    }

}

fun LazyListScope.section(
    icon: ImageVector,
    section: String,
    items: List<Reference>
) {
    if (items.isEmpty()) return
    item {
        Text(
            text = section,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            headlineContent = { Text(it.name) },
            leadingContent = { Icon(imageVector = icon, contentDescription = null) }
        )
    }
}

@Composable
fun Header(character: Character) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        if (character.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp, 0.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(widthDp = 400, heightDp = 700)
@Composable
fun CharacterDetailScreenPreview() {
    val character = Character(
        id = 1,
        name = "Character Name",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dolor lacus, sollicitudin ac eros ut, commodo ullamcorper est. Ut quis metus id elit tincidunt convallis. Sed sit amet turpis ac ipsum fermentum sollicitudin. Etiam quis est vel dolor molestie suscipit. Fusce ut dui sed neque iaculis ultrices a eget velit.",
        thumbnail = "",
        comics = listOf(Reference("Comic 1"), Reference("Comic 2")),
        events = listOf(Reference("Comic 1"), Reference("Comic 2")),
        stories = listOf(Reference("Comic 1"), Reference("Comic 2")),
        series = listOf(Reference("Comic 1"), Reference("Comic 2"))
    )
    MarvelApp {
        CharacterDetailScreen(character)
    }
}