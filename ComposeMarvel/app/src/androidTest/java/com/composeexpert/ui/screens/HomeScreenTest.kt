package com.composeexpert.ui.screens

import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.platform.app.InstrumentationRegistry
import arrow.core.Either
import com.composeexpert.data.entities.Comic
import com.composeexpert.ui.screens.common.MarvelItemsListScreen
import com.example.composeexpert.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    private val items = (1..100).map {
        Comic(
            id = it,
            title = "Title $it",
            description = "Description $it",
            thumbnail = "",
            references = emptyList(),
            urls = emptyList(),
            format = Comic.Format.COMIC,
        )
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemsListScreen(
                items = Either.Right(items),
                onClick = { },
                isLoading = false
            )
        }
    }

    @Test
    fun navigateToItem25(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNodeWithText("Title 25").assertExists()
    }
    @Test
    fun navigateToItem25AndShowBottomSheet(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNode(
            hasParent(hasText("Title 25")) and
            hasContentDescription(ctx.getString(R.string.more_actions))
        ).performClick()

        onNode(
            hasAnySibling(hasText(ctx.getString(R.string.go_to_detail))) and
            hasText("Title 25")
        ).assertExists()
    }
}