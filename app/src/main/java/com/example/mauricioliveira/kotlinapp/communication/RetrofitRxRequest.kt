package com.example.mauricioliveira.kotlinapp.communication

import com.example.mauricioliveira.kotlinapp.communication.model.ApiToken
import com.example.mauricioliveira.kotlinapp.communication.model.ReturnData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRxRequest {
    @GET(CommEndPoint.AMOUNT10)
    fun getReturnData(@Query("category") category:String, @Query("token") token: String) : Observable<ReturnData>?

    @GET(CommEndPoint.API_TOKEN)
    fun getApiToken() : Observable<ApiToken>?
}