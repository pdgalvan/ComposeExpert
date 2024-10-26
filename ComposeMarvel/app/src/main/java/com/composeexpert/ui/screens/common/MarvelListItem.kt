package com.composeexpert.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.composeexpert.data.entities.MarvelItem
import com.composeexpert.ui.navigation.AppBarIcon
import com.example.composeexpert.R

@Composable
fun <T: MarvelItem> MarvelListItem(
    marvelItem: T,
    onClickMore: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            AsyncImage(
                model = marvelItem.thumbnail,
                contentDescription = marvelItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = marvelItem.title,
                modifier = Modifier.padding(8.dp, 16.dp).weight(1f),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
            )
            AppBarIcon(
                onClick = { onClickMore(marvelItem) },
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.more_actions)
            )
        }
    }
}