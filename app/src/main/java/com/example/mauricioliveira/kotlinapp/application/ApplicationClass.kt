package com.example.mauricioliveira.kotlinapp.application

import android.app.Application
import android.content.Context
import com.example.mauricioliveira.kotlinapp.communication.ServiceController

class ApplicationClass : Application() {

    private var serviceComm: ServiceController? = null

    override fun onCreate() {
        super.onCreate()
        applicationClass = this
        context = applicationContext
        serviceComm = ServiceController.getInstance(context)

    }

    fun getService () : ServiceController? {
        return serviceComm
    }

    companion object {
        var context: Context? = null
        var applicationClass: ApplicationClass? = null

        fun getInstance() : ApplicationClass? {
            return applicationClass
        }
    }
}
