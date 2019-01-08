package com.example.mauricioliveira.kotlinapp.public.register

import java.lang.Exception

interface RegisterPresenterInterface {
    fun doRegister(username:String,email:String, password:String, listener:RegisterTaskInterface)

    fun doRegisterDatabase(username: String, email: String, uid: String)

    interface RegisterTaskInterface {
        fun onRegisterSuccess(username: String, email: String, uid: String)
        fun onRegisterFailure(exception: Exception?)
    }
}