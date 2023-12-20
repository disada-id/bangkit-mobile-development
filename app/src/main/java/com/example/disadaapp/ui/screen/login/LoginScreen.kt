package com.example.disadaapp.ui.screen.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.TextStyle
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
import com.example.disadaapp.ui.Component.TextFieldWithValidation
import com.example.disadaapp.Utils.ValidationUtil
import com.example.disadaapp.ui.theme.DullPink
import com.example.disadaapp.ui.theme.poppinsFamily
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
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

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
        ) {
            //display logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Disada logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
            
            //display heading
            Text(
                text = "Welcome Back !",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000),
                )
            )

            //display paragraf
            Text(
                text = stringResource(R.string.dummy_text),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                TextFieldWithValidation(
                    value = email, // Ganti dengan data yang sesuai
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    label = stringResource(id = R.string.email_TextField),
                    imeAction = ImeAction.Next,
                    validation = { ValidationUtil.isEmailValid(it) },
                    validationHint = stringResource(id = R.string.validateEmailError),
                    iconResId = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                // Password input field
                TextFieldWithValidation(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    label = stringResource(id = R.string.password_textField),
                    imeAction = ImeAction.Done,
                    validation = { ValidationUtil.isPasswordValid(it) },
                    validationHint = stringResource(id = R.string.validatePasswordError),
                    iconResId = Icons.Default.Lock,
                    isPasswordToggleEnabled = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                Button(
                    onClick = {
                              viewModel.signin(email, password)
                    },
                    colors = ButtonDefaults.buttonColors(DullPink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                       text =  stringResource(R.string.login),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    IconButton(onClick = {
                        val buildGoogleSignInRequest = GoogleSignInOptions.Builder(
                            GoogleSignInOptions.DEFAULT_SIGN_IN)
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
                                Toast.makeText(context, "sign in success", Toast.LENGTH_SHORT).show()
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
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal
                                )
                            ){
                                append("Don't have an account?")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Medium
                                )
                            ){
                                append(" ")
                                append("Register")
                            }
                        }
                    )
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    MaterialTheme {
        LoginScreen()
    }
}