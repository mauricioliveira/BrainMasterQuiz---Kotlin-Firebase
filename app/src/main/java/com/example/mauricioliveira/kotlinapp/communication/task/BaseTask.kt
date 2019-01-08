package com.example.mauricioliveira.kotlinapp.communication.task

import com.example.mauricioliveira.kotlinapp.application.ApplicationClass
import com.example.mauricioliveira.kotlinapp.communication.ServiceController
import io.reactivex.disposables.CompositeDisposable

open class BaseTask {

    var serviceController: ServiceController? = null
    var mCompositeDisposable: CompositeDisposable? = null

    constructor() {
        this.serviceController = ApplicationClass.getInstance()?.getService()
        this.mCompositeDisposable = CompositeDisposable()
    }
}