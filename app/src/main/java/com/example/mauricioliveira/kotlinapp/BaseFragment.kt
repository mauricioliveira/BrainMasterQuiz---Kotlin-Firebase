package com.example.mauricioliveira.kotlinapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {

    private var mListener: MainActivityListener? = null
    var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivityListener) {
            mListener = context
        }
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun setToolbarTitle(string: String) {
        mListener?.let { it.setActionTitle(string) }
    }

    fun finishActivity() {
        mListener?.let { it.finishActivity() }
    }

    fun setDisplayHome(boolean: Boolean) {
        mListener?.let { it.setDisplayHomeEnable(boolean) }
    }
}