package com.example.mauricioliveira.kotlinapp.communication.interfaces

import com.example.mauricioliveira.kotlinapp.communication.model.ReturnData

interface GetAmountTaskListener {
    fun onGetAmountTaskSuccess(returnData: ReturnData)
    fun onGetAmountTaskUnSucess(error: Throwable)
}