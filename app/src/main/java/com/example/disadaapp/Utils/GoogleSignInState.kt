package com.example.disadaapp.Utils

import com.google.firebase.auth.AuthResult

class GoogleSignInState (
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
    )