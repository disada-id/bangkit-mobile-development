package com.example.disadaapp.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disadaapp.R
import com.example.disadaapp.Utils.ValidationUtil
import com.example.disadaapp.ui.Component.TextFieldCustom
import com.example.disadaapp.ui.Component.TextFieldWithValidation
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.DullPink
import com.example.disadaapp.ui.theme.poppinsFamily

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


    fun register (
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
//        if(ValidationUtil(username, email, phoneNumber, password)) {
//            Toast.makeText(context, "Hi $username your account is ready now please login with email and password that registered", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//        }
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
            text = "Register!",
            modifier = Modifier,
            fontSize = 30.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldWithValidation(
            value = username, // Ganti dengan data yang sesuai
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 40.dp, end = 40.dp),
            label = stringResource(id = R.string.username_label),
            imeAction = ImeAction.Next,
            validation = { ValidationUtil.isUsernameValid(it) },
            validationHint = stringResource(id = R.string.validateNameError),
            iconResId = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)})
        )
        TextFieldWithValidation(
            value = email, // Ganti dengan data yang sesuai
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 40.dp, end = 40.dp),
            label = stringResource(id = R.string.email_TextField),
            imeAction = ImeAction.Next,
            validation = { ValidationUtil.isEmailValid(it) },
            validationHint = stringResource(id = R.string.validateEmailError),
            iconResId = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)})
        )
        TextFieldWithValidation(
            value = phoneNumber, // Ganti dengan data yang sesuai
            onValueChange = { phoneNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 40.dp, end = 40.dp),
            label = stringResource(id = R.string.phone_label),
            imeAction = ImeAction.Next,
            validation = { ValidationUtil.isPhoneNumberValid(it) },
            validationHint = stringResource(id = R.string.validatePhoneNumberError),
            iconResId = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Down)})
        )
        TextFieldWithValidation(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 40.dp, end = 40.dp),
            label = stringResource(id = R.string.password_textField),
            imeAction = ImeAction.Done,
            validation = { ValidationUtil.isPasswordValid(it) },
            validationHint = stringResource(id = R.string.validatePasswordError),
            iconResId = Icons.Default.Lock,
            isPasswordToggleEnabled = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(onNext = {focusManager.clearFocus()})
        )

        Button(
            onClick = {
                      register(username, email, phoneNumber, password)
            },
            colors = ButtonDefaults.buttonColors(DullPink),
            modifier = Modifier
                .padding(20.dp)
                .width(250.dp),
        ) {
            Text(text = "Sign Up", fontFamily = poppinsFamily,fontSize = 20.sp)
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