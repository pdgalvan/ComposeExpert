package com.example.loginsample.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.loginsample.R

const val PASSWORD_TEXT_FIELD_TEST_TAG = "PasswordTextFieldTestTag"
const val PASSWORD_ICON_SHOW_TEST_TAG = "PasswordIconShowTestTag"

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text("Password") },
        placeholder = { Text("Input your password") },
        trailingIcon = {
            IconToggleButton(
                checked = isPasswordVisible,
                onCheckedChange = { isPasswordVisible = it },
                modifier = Modifier.testTag(PASSWORD_ICON_SHOW_TEST_TAG)
            ) {
                Crossfade(
                    targetState = isPasswordVisible,
                    label = "crossfade",
                    animationSpec = tween(2000)
                ) { visible ->
                    if (visible) {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = stringResource(R.string.hide_password)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = stringResource(R.string.show_password)
                        )
                    }
                }
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.testTag(PASSWORD_TEXT_FIELD_TEST_TAG)
    )
}