package com.example.loginsample.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.loginsample.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var password by remember { mutableStateOf("") }
            PasswordTextField(
                value = password,
                onValueChange = { password = it }
            )
        }
    }

    @Test
    fun revealIconShowsPassword(): Unit = with(composeTestRule) {
        onNodeWithText("").performTextInput("pass")
        onNodeWithText("••••").assertExists()

        onNodeWithContentDescription(ctx.getString(R.string.show_password)).performClick()
        onNodeWithText("pass").assertExists()
        onNodeWithContentDescription(ctx.getString(R.string.hide_password)).assertExists()
    }
}