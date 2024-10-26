package com.composeexpert.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.composeexpert.data.entities.Character
import com.composeexpert.data.entities.MarvelItem
import com.example.composeexpert.R

@Composable
fun <T : MarvelItem> MarvelItemBottom(
    marvelItem: T?,
    onClickDetail: (T) -> Unit,
) {
    if(marvelItem != null) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = marvelItem.thumbnail,
                contentDescription = marvelItem.title,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = marvelItem.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = marvelItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Button(
                    onClick = { onClickDetail(marvelItem) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(R.string.go_to_detail))
                }
            }
        }
    }
}

@Preview
@Composable
fun MarvelItemBottomPrev() {

    MarvelItemBottom(
        marvelItem = Character(
            id = 1011334,
            title = "3-D Man",
            description = "asdasd asdas dasd asd asdasdlak kladlasklñdñklasdlñ",
            thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
            references = emptyList(),
            urls = emptyList()
        ),
        onClickDetail =  { }
    )
}
