package com.example.mauricioliveira.kotlinapp

interface MainActivityListener {
    fun finishActivity()
    fun setActionTitle (string: String)
    fun setDisplayHomeEnable (boolean: Boolean)
}