package com.example.disadaapp.ui.screen.register

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disadaapp.R
import com.example.disadaapp.ui.Component.TextFieldCustom
import com.example.disadaapp.ui.theme.DisadaAppTheme

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var username by rememberSaveable { mutableStateOf("")}
    var email by rememberSaveable { mutableStateOf("")}
    var phoneNumber by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}

    var validateUsername by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhoneNumber by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    //function to validate data
    fun validateData(username : String, email: String, phoneNumber: String, password: String): Boolean {
        val passwordPattern = "\"^.{1,8}\$\"".toRegex()

        validateUsername = username.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhoneNumber = Patterns.PHONE.matcher(phoneNumber).matches()
        validatePassword = passwordPattern.matches(password)

        return validateUsername && validateEmail && validatePhoneNumber && validatePassword
    }

    fun register (
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        if(validateData(username, email, phoneNumber, password)) {
            Toast.makeText(context, "Hi $username your account is ready now please login with email and password that registered", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    //ui
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .size(150.dp)
        )
        Text(
            text = "Welcome!",
            modifier = Modifier,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldCustom(
            value = username,
            onValueChange = { username = it },
            leadingIconImageVector = Icons.Default.PermIdentity,
            showError = !validateUsername,
            errorMessage = stringResource(R.string.validateNameError),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            label = "Username"
        )
        TextFieldCustom(
            value = email,
            onValueChange = { email = it },
            leadingIconImageVector = Icons.Default.Email,
            showError = !validateEmail,
            errorMessage = stringResource(R.string.validateEmailError),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            label = "Email"
        )
        TextFieldCustom(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            leadingIconImageVector = Icons.Default.Phone,
            showError = !validatePhoneNumber,
            errorMessage = stringResource(R.string.validatePhoneNumberError),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            label = "Phone Number"
        )
        TextFieldCustom(
            value = password,
            onValueChange = { password = it },
            leadingIconImageVector = Icons.Default.Lock,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = {isPasswordVisible = it},
            showError = !validatePassword,
            errorMessage = stringResource(R.string.validatePasswordError),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            label = "Password"
        )

        Button(
            onClick = {
                      register(username, email, phoneNumber, password)
            },
            modifier = Modifier
                .padding(20.dp)
                .width(250.dp),
        ) {
            Text(text = "Sign Up", fontSize = 20.sp)
        }
        Column(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            //contentAlignment = Alignment.BottomCenter,
        ) {
            Row() {
                Text("Already have an account? Login")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DisadaAppPreview() {
    DisadaAppTheme {
        RegisterContent()
    }
}