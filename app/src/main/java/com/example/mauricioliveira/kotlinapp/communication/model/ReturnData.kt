package com.example.mauricioliveira.kotlinapp.communication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReturnData(
        @SerializedName("response_code") val responseCode: Int,
        @SerializedName("results") val results: List<Result>
) : Serializable