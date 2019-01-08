package com.example.mauricioliveira.kotlinapp.database

class PersistentData private constructor() {

    private object Holder {
        val INSTANCE = PersistentData()
    }

    companion object {
        val instance: PersistentData by lazy { Holder.INSTANCE }
    }

    lateinit var apiToken: String
}