package com.example.disadaapp.ui.screen.register

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.disadaapp.R
import com.example.disadaapp.Utils.AuthViewModel
import com.example.disadaapp.Utils.Constant
import com.example.disadaapp.Utils.ValidationUtil
import com.example.disadaapp.data.network.ApiResponse
import com.example.disadaapp.ui.Component.TextFieldWithValidation
import com.example.disadaapp.ui.theme.DisadaAppTheme
import com.example.disadaapp.ui.theme.DullPink
import com.example.disadaapp.ui.theme.poppinsFamily
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen (
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onRegistrationSuccess: () -> Unit,
) {
    val googleSignInState = viewModel.googleState.value
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            }  catch (e: ApiException) {
                print(e)
            }
        }

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var fullname by rememberSaveable { mutableStateOf("") }
    var nohp by rememberSaveable { mutableStateOf("") }

    //Observer untuk status API
    val apiState by viewModel.apiState.collectAsState()
    var isLoading by rememberSaveable { mutableStateOf(false) }


    //ui
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
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
                value = fullname, // Ganti dengan data yang sesuai
                onValueChange = { fullname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 40.dp, end = 40.dp),
                label = stringResource(id = R.string.fullname_label),
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
                value = nohp, // Ganti dengan data yang sesuai
                onValueChange = { nohp = it },
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
                    isLoading = true
                    viewModel.signup(email, password, username, fullname, nohp)
                        when (apiState) {
                            is ApiResponse.Error -> {
                                isLoading = false
                            }
                            is ApiResponse.Success -> {
                                if (!isLoading) {
                                    // Panggil callback jika pendaftaran berhasil
                                    onRegistrationSuccess.invoke()
                                }
                                isLoading = false
                            }

                            else -> {}
                        }

                },
                colors = ButtonDefaults.buttonColors(DullPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp, vertical = 10.dp),
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text(text = "Sign Up", fontFamily = poppinsFamily, fontSize = 20.sp)
                }
            }



            //firebase
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(onClick = {
                    val buildGoogleSignInRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(Constant.firebase_server_client)
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, buildGoogleSignInRequest)
                    launcher.launch(googleSignInClient.signInIntent)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = "Google SignIn",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Unspecified
                    )
                }
                LaunchedEffect(key1 = googleSignInState.success) {
                    scope.launch {
                        if (googleSignInState.success != null) {
                            Toast.makeText(context, "Sign With Google Success", Toast.LENGTH_SHORT).show()
                            onRegistrationSuccess.invoke()
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.8f)
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = poppinsFamily
                        )
                    ) {
                        append("Already have an account?")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppinsFamily
                        )
                    ) {
                        append(" ")
                        append("Login")
                    }
                })
            }
        }
    }
}




@Composable
fun Snackbar(modifier: Modifier, text: String) {
    Text(text = "error Register")
}

@Preview(showBackground = true)
@Composable
fun DisadaAppPreview() {
    DisadaAppTheme {
        RegisterScreen(
            onRegistrationSuccess = {}
        )
    }
}