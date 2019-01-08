package com.example.mauricioliveira.kotlinapp.communication.task

import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetApiTokenTaskListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetApiTokenTask (listener: GetApiTokenTaskListener) : BaseTask() {

    var listener: GetApiTokenTaskListener = listener

    fun getApiToken() {
        this.serviceController?.getSOServiceRX()?.getApiToken()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ response ->
                    this.listener?.onGetApiTokenTaskSuccess(response)
                }, { error ->
                    this.listener?.onGetApiTokenTaskUnSucess(error)
                })
    }
}