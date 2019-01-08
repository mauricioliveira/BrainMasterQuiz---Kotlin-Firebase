package com.example.mauricioliveira.kotlinapp.firebase

import android.app.Activity
import com.example.mauricioliveira.kotlinapp.BaseActivity
import com.google.firebase.auth.FirebaseAuth

open class FirebaseAuthBase {
    var authInstance: FirebaseAuth? = null
    var baseActivity: Activity

    constructor() {
        authInstance = FirebaseAuth.getInstance()
        baseActivity = BaseActivity().getActivity()
    }
}