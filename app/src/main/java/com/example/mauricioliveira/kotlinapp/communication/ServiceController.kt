package com.example.mauricioliveira.kotlinapp.communication

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceController (context: Context?) {

    fun getSOServiceRX() : RetrofitRxRequest? {
        return getClient()?.create(RetrofitRxRequest::class.java)
    }

    fun getClient () : Retrofit? {
        if (retrofit == null) {
            val httpClient: OkHttpClient = getOkHttpClient()

            retrofit = Retrofit.Builder()
                    .baseUrl(CommEndPoint.BASEURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
        }

        return retrofit
    }

    fun getOkHttpClient() : OkHttpClient {
        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120,TimeUnit.SECONDS)
                .build()

        return okHttpClient
    }

    companion object {
        var instance: ServiceController? = null

        var retrofit: Retrofit? = null

        fun getInstance (context: Context?) : ServiceController? {
            if (instance == null) {
                instance = ServiceController(context)
            }
            return instance
        }
    }
}