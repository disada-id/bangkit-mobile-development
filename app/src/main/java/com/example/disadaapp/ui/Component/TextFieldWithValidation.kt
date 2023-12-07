package com.example.disadaapp.ui.Component

// Custom TextField component for Jetpack Compose with input validation logic
// Used for login and register screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithValidation(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    imeAction: ImeAction = ImeAction.Done,
    validation: (String) -> Boolean,
    validationHint: String,
    iconResId: ImageVector,
    isPasswordToggleEnabled: Boolean = false,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    var isHintVisible by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // visual transformation
    var visualTransformation: VisualTransformation = VisualTransformation.None
    if (isPasswordToggleEnabled) {
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            // Tampilkan/sembunyikan petunjuk berdasarkan hasil validasi
            isHintVisible = !validation(it)
        },
        modifier = modifier,
        label = { Text(label, color = if (isHintVisible) Color.Red else LocalTextStyle.current.color) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        leadingIcon = {
            Icon(
                imageVector = iconResId,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isPasswordToggleEnabled) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            }
        },
        visualTransformation = visualTransformation
    )

    // Tampilkan petunjuk validasi jika diperlukan
    if (isHintVisible) {
        Text(
            text = validationHint,
            color = Color.Red,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
