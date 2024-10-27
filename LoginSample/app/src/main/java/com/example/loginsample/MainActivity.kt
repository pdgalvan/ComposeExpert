package com.example.loginsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginsample.ui.theme.LoginSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login()
        }
    }
}


@Composable
fun Login() {
    LoginSampleTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            LoginScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }
    val isButtonEnabled by remember { derivedStateOf { user.isNotBlank() && password.isNotBlank() } }
    val isError = validationMessage.isNotBlank()
    val login = {
        validationMessage = validateLogin(user, password)
    }
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        TextField(
            isError = isError,
            value = user,
            onValueChange = { user = it },
            singleLine = true,
            label = { Text("User") },
            placeholder = { Text("Input your user") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )
        TextField(
            isError = isError,
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
            placeholder = { Text("Input your password") },
            trailingIcon = {
                IconToggleButton(
                    checked = isPasswordVisible,
                    onCheckedChange = { isPasswordVisible = it }
                ) {
                    Crossfade(
                        targetState = isPasswordVisible,
                        label = "",
                        animationSpec = tween(2000)
                    ) { visible ->
                        if (visible) {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { login() }
            )
        )
        AnimatedVisibility(
            visible = validationMessage.isNotBlank(),
            enter = slideInHorizontally(initialOffsetX = { 2 * it })
        ) {
            Text(text = validationMessage, color = MaterialTheme.colorScheme.error)
        }
        AnimatedVisibility(visible = isButtonEnabled) {
            Button(
                onClick = login
            ) {
                Text(text = "Login")
            }
        }
        //AnimatedContentComponent()
    }
}

@Composable
fun AnimatedContentComponent() {
    var counter by remember { mutableIntStateOf(0) }
    Button( onClick =  {
        counter++
    }){
        Text("Increase counter")
    }
    AnimatedContent(
        targetState = counter,
        transitionSpec = {
            (slideIntoContainer(Up) + fadeIn())
                .togetherWith(slideOutOfContainer(Up) + fadeOut())
        },
        label = ""
    ){
        Text("Click counter $it")
    }
}

fun validateLogin(user: String, password: String): String {
    val result = when {
        !user.contains("@") -> "User must be a valid email"
        password.length < 5 -> "Password must have at least 5 characters"
        else -> ""
    }
    return result
}

@Preview
@Composable
fun LoginPrev() {
    Login()
}

