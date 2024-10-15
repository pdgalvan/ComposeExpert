package com.composeexpert.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.composeexpert.data.entities.MarvelItem

@Composable
fun MarvelListItem(
    item: MarvelItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberAsyncImagePainter(item.thumbnail),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Box(modifier = Modifier.padding(8.dp, 16.dp)) {
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp, 16.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
            )
        }
    }
}