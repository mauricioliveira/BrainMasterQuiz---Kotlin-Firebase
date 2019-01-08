package com.example.mauricioliveira.kotlinapp.public.register

import com.example.mauricioliveira.kotlinapp.firebase.FirebaseAuthBase
import com.example.mauricioliveira.kotlinapp.firebase.FirebaseDatabaseBase
import com.example.mauricioliveira.kotlinapp.objects.User

class RegisterCallService : FirebaseAuthBase(), RegisterPresenterInterface {

    override fun doRegister(username: String, email: String, password: String, listener: RegisterPresenterInterface.RegisterTaskInterface) {
        this.authInstance!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.baseActivity) { task ->
                    when {
                        task.isSuccessful -> listener.onRegisterSuccess(username,email,this.authInstance!!.currentUser!!.uid)
                        else -> listener.onRegisterFailure(task.exception)
                    }
                }
    }

    override fun doRegisterDatabase(username: String, email: String, uid: String) {
        var user = User(username, email)
        FirebaseDatabaseBase.usersReference.child(uid).setValue(user)
    }
}