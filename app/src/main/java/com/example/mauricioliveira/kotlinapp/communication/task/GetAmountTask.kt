package com.example.mauricioliveira.kotlinapp.communication.task

import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetAmountTaskListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetAmountTask(listener: GetAmountTaskListener) : BaseTask() {

    var listener: GetAmountTaskListener? = null

    init {
        this.listener = listener
    }

    fun getAmount(category: String, token: String) {
        this.serviceController?.getSOServiceRX()?.getReturnData(category,token)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ response ->
                    this.listener?.onGetAmountTaskSuccess(response)
                }, { error ->
                    this.listener?.onGetAmountTaskUnSucess(error)
                })
    }
}