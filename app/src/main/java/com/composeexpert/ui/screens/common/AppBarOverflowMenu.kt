package com.composeexpert.ui.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import com.composeexpert.data.entities.Url

@Composable
fun AppBarOverflowMenu(urls: List<Url>) {
    if (urls.isEmpty()) return
    var isDropdownVisible by rememberSaveable { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current
    IconButton(onClick = { isDropdownVisible = !isDropdownVisible }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
        )
        DropdownMenu(
            expanded = isDropdownVisible,
            onDismissRequest = { isDropdownVisible = false }
        ) {
            urls.forEach {
                DropdownMenuItem(
                    onClick = {
                        uriHandler.openUri(it.url)
                        isDropdownVisible = false
                    },
                    text = { Text(it.type) }
                )
            }
        }
    }
}