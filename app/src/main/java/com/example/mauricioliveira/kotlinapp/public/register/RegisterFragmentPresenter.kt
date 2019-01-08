package com.example.mauricioliveira.kotlinapp.public.register

import android.content.Context
import android.widget.Toast
import java.lang.Exception

class RegisterFragmentPresenter (registerFragment: RegisterViewInterface, mContext: Context?) :  RegisterPresenterInterface.RegisterTaskInterface {

    private var registerView: RegisterViewInterface? = registerFragment
    private var registerInteractor: RegisterPresenterInterface = RegisterCallService()
    private var mContext = mContext


    fun doRegist(username: String,email:String, password: String, confirmPassword: String) {
        if (validateFields(username,password,confirmPassword)) {
            this.registerInteractor.doRegister(username,email,password,this)
        }
    }

    private fun validateFields(username: String, password: String, confirmPassword: String): Boolean {
        var valid = false

        if (username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                valid = true
            }
        }

        return valid
    }

    fun onDestroy() {
        this.registerView = null
    }

    override fun onRegisterSuccess(username: String, email: String, uid: String) {
        this.registerInteractor.doRegisterDatabase(username,email,uid)
        this.registerView?.let { it.goToLogin() }
    }

    override fun onRegisterFailure(exception: Exception?) {
        Toast.makeText(mContext,exception?.message,Toast.LENGTH_LONG)
    }
}