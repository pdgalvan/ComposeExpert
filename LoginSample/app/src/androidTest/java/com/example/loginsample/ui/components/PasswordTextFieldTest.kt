package com.example.loginsample.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

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

        onNodeWithTag(PASSWORD_TEXT_FIELD_TEST_TAG).performTextInput("pass")
        onNodeWithTag(PASSWORD_TEXT_FIELD_TEST_TAG).assertTextContains("••••")

        onNodeWithTag(PASSWORD_ICON_SHOW_TEST_TAG).performClick()

        onNodeWithTag(PASSWORD_TEXT_FIELD_TEST_TAG).assertTextContains("pass")
        onNodeWithTag(PASSWORD_ICON_SHOW_TEST_TAG).assertExists()
    }
}