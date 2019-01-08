package com.example.mauricioliveira.kotlinapp

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import com.example.mauricioliveira.kotlinapp.public.login.LoginFragment

class MainActivity : BaseActivity(), MainActivityListener{

    private lateinit var fragmentManager: FragmentManager
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.toolbar = findViewById(R.id.include_toolbar)

        setSupportActionBar(this.toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this.initDefaultValues()
        this.loadFirstFragment()
    }

    private fun loadFirstFragment() {
        var fragment = LoginFragment.newInstance()
        this.fragmentManager.beginTransaction()
                .replace(R.id.main_fl,fragment)
                .commit()
    }

    private fun initDefaultValues() {
        this.fragmentManager = this.supportFragmentManager
    }

    override fun setActionTitle(string: String) {
        this.setActionBarTitle(string)
    }

    override fun finishActivity() {

    }

    override fun setDisplayHomeEnable (boolean: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(boolean)
    }

}
