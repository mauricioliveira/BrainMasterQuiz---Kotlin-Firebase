package com.example.mauricioliveira.kotlinapp.public.login

import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetApiTokenTaskListener
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

interface LoginPresenterInterface {
    fun login (username: String, password: String, listener: LoginTaskInterface)

    interface LoginTaskInterface {
        fun onLoginSuccess(currentUser: FirebaseUser?)
        fun onLoginFailure(exception: Exception?)
    }
}