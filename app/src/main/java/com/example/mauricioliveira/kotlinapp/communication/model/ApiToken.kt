package com.example.mauricioliveira.kotlinapp.communication.model

import com.google.gson.annotations.SerializedName

data class ApiToken(
        @SerializedName("response_code") val responseCode: Int,
        @SerializedName("response_message") val responseMessage: String,
        @SerializedName("token") val token: String
)