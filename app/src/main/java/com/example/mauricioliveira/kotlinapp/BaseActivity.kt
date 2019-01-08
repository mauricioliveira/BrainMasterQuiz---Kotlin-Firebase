package com.example.mauricioliveira.kotlinapp

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setActionBarTitle (string: String) {
        /*var textView = findViewById<TextView>(R.id.toolbar_title)
        textView?.text = string*/
        supportActionBar?.title = string
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    fun getActivity() : Activity = this@BaseActivity
}