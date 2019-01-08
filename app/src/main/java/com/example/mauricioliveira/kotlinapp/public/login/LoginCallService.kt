package com.example.mauricioliveira.kotlinapp.public.login

import com.example.mauricioliveira.kotlinapp.firebase.FirebaseAuthBase

class LoginCallService : FirebaseAuthBase(), LoginPresenterInterface {

    override fun login(username: String, password: String, listener: LoginPresenterInterface.LoginTaskInterface) {
        this.authInstance!!.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(baseActivity) { task ->
                    if (task.isSuccessful) {
                        listener.onLoginSuccess(authInstance!!.currentUser)
                    } else {
                        listener.onLoginFailure(task.exception)
                    }
                }
    }

}