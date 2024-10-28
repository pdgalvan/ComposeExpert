package com.example.loginsample

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test


class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickButton(): Unit = with(composeTestRule) {
        setContent {
            var text by remember { mutableStateOf("Hello") }
            Button(onClick = { text = "Bye" }) {
                Text(text = text)
            }
        }
        onNodeWithText("Hello").performClick()
        onNodeWithText("Bye").assertExists()
    }
}