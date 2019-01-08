package com.example.mauricioliveira.kotlinapp.controller

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mauricioliveira.kotlinapp.R

class LoginInput : LinearLayout {

    private val INPUT_TYPE_TEXT = 0
    private val INPUT_TYPE_TEXT_PASSWORD = 1

    private var imageView: ImageView? = null
    private var editText: EditText? = null

    private var text: String? = null

    private var inputType: Int = 0

    private var drawable: Drawable? = null

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr) {
        initializeView(context, attrs, defStyleAttr, 0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {

        initializeView(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

        LayoutInflater.from(context).inflate(R.layout.login_input, this, true)

        attrs?.let { getAttributesFromXmlAndStoreLocally(it, context) }

        this.editText = findViewById(R.id.editTextLogin)

        this.imageView = findViewById(R.id.imageViewLogin)

        setValues()
    }

    private fun setValues() {
        this.text?.let { this.editText?.setHint(it) }

        this.inputType?.let {
            if (it == INPUT_TYPE_TEXT) {
                this.editText?.inputType = InputType.TYPE_CLASS_TEXT
            } else if (it == INPUT_TYPE_TEXT_PASSWORD) {
                this.editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        this.drawable?.let {
            this.imageView?.setImageDrawable(it)
        }
    }

    private fun getAttributesFromXmlAndStoreLocally(attributeSet: AttributeSet, context: Context) {
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoginInput)

        this.text = typeArray.getString(R.styleable.LoginInput_text)

        this.inputType = typeArray.getInt(R.styleable.LoginInput_inputType, 0)

        this.drawable = typeArray.getDrawable(R.styleable.LoginInput_drawable)

        typeArray.recycle()
    }

    fun getEditText () : EditText? {
        return this.editText
    }
}