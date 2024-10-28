package com.example.loginsample.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            LoginScreen()
        }
    }

    @Test
    fun loginButtonIsHidden(): Unit = with(composeTestRule) {
        onNodeWithText("User").performTextInput("user")

        onNodeWithText("Login").assertDoesNotExist()
    }

    @Test
    fun loginButtonAppearsWhenInputPassword(): Unit = with(composeTestRule) {
        onNodeWithText("User").performTextInput("user")
        onNodeWithText("Password").performTextInput("pass")

        onNodeWithText("Login").assertExists()
    }
}