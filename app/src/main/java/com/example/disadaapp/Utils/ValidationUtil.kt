package com.example.disadaapp.Utils

import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtil {

    // Logika validasi email
    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        val pattern = Pattern.compile(emailRegex)
        return pattern.matcher(email).matches()
    }

    // Logika validasi password
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    fun isUsernameValid(username: String) : Boolean{
        return username.isNotBlank()
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean{
        return Patterns.PHONE.matcher(phoneNumber).matches()
    }
}