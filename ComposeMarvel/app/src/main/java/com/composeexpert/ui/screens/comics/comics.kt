package com.composeexpert.ui.screens.comics

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.composeexpert.data.entities.Comic
import com.composeexpert.ui.screens.common.ErrorScreen
import com.composeexpert.ui.screens.common.MarvelItemDetailScreen
import com.composeexpert.ui.screens.common.MarvelItemsListScreen
import com.example.composeexpert.R
import kotlinx.coroutines.launch

@Composable
fun ComicsScreen(
    onClick: (Comic) -> Unit,
    viewModel: ComicsViewModel = hiltViewModel()
) {
    val formats = Comic.Format.entries
    val pagerState = rememberPagerState(pageCount = { formats.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        ) {
            formats.forEach { format ->
                Tab(
                    selected = format.ordinal == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(format.ordinal)
                        }
                    },
                    text = { Text(text = stringResource(format.toStringRes())) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { page ->
            val format = formats[page]
            viewModel.formatRequested(format)

            val pageState by viewModel.state.getValue(format).collectAsState()
            pageState.comics.fold({ ErrorScreen(it) }) {
                MarvelItemsListScreen(
                    isLoading = pageState.isLoading,
                    items = pageState.comics,
                    onClick = onClick,
                )
            }
        }
    }
}

@StringRes
fun Comic.Format.toStringRes(): Int = when (this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER -> R.string.hardcover
    Comic.Format.DIGEST -> R.string.digest
    Comic.Format.GRAPHIC_NOVEL -> R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC -> R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
}

@Composable
fun ComicDetailScreen(viewmodel: ComicDetailViewModel = hiltViewModel()) {
    val state by viewmodel.state.collectAsState()

    MarvelItemDetailScreen(
        isLoading = state.isLoading,
        marvelItem = state.comic,
    )
}