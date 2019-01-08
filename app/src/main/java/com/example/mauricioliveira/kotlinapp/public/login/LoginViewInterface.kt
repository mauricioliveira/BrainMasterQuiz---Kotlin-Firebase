package com.example.mauricioliveira.kotlinapp.public.login

import com.google.firebase.auth.FirebaseUser

interface LoginViewInterface {
    fun goToRegister()
    fun goToPrivate(currentUser: FirebaseUser?)
    fun showLoginFailure()
    fun showLoading()
    fun hideLoading()
}