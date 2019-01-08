package com.example.mauricioliveira.kotlinapp.communication.interfaces

import com.example.mauricioliveira.kotlinapp.communication.model.ApiToken

interface GetApiTokenTaskListener {

    fun onGetApiTokenTaskSuccess(apiToken: ApiToken)
    fun onGetApiTokenTaskUnSucess(error: Throwable)
}