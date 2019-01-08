package com.example.mauricioliveira.kotlinapp.public.login

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class LoginFragmentPresenter (loginView: LoginViewInterface) : LoginPresenterInterface.LoginTaskInterface {

    private var loginView: LoginViewInterface? = loginView
    private var loginInteractor: LoginPresenterInterface = LoginCallService()

    fun doLogin(username: String, password: String) {
        if (validateFields(username,password)) {
            this.loginInteractor.login(username,password,this)
        }
    }

    private fun validateFields(username: String, password: String): Boolean {
        var valid = false
        if (username.isNotEmpty() && password.isNotEmpty()) {
            valid = true
        }

        return valid
    }

    fun doRegister() {
        this.loginView?.let { it.goToRegister() }
    }

    fun onDestroy() {
        this.loginView = null
    }


    override fun onLoginSuccess(currentUser: FirebaseUser?) {
        Log.d("currentUser",currentUser?.email)
        this.loginView?.let { it.goToPrivate(currentUser) }
    }

    override fun onLoginFailure(exception: Exception?) {
        this.loginView?.let {
            it.showLoginFailure()
        }
    }

}