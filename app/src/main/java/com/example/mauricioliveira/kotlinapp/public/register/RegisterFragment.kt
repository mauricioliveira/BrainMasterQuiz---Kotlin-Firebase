package com.example.mauricioliveira.kotlinapp.public.register


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mauricioliveira.kotlinapp.BaseFragment
import com.example.mauricioliveira.kotlinapp.R
import com.example.mauricioliveira.kotlinapp.controller.LoginInput
import com.example.mauricioliveira.kotlinapp.public.login.LoginFragment


class RegisterFragment : BaseFragment(), View.OnClickListener, RegisterViewInterface {

    private lateinit var username: LoginInput
    private lateinit var password: LoginInput
    private lateinit var confirmPassword: LoginInput
    private lateinit var email: LoginInput
    private lateinit var registerButton: Button
    private lateinit var mPresenter: RegisterFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_register, container, false)

        this.username = rootView.findViewById(R.id.username_input)
        this.email = rootView.findViewById(R.id.email_input)
        this.password = rootView.findViewById(R.id.password_input)
        this.confirmPassword = rootView.findViewById(R.id.confirm_password_input)

        this.registerButton = rootView.findViewById(R.id.registerButton)
        this.registerButton.setOnClickListener(this)

        this.mPresenter = RegisterFragmentPresenter(this,mContext)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        this.setToolbarTitle("Register")
        this.setDisplayHome(true)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            this.registerButton -> this.mPresenter.doRegist(this.username.getEditText()?.text.toString(),
                    this.email.getEditText()?.text.toString(),
                    this.password.getEditText()?.text.toString(),
                    this.confirmPassword.getEditText()?.text.toString())
        }
    }

    override fun goToLogin() {
        var fragment = LoginFragment.newInstance()
        this.fragmentManager!!.beginTransaction()
                .replace(R.id.main_fl, fragment)
                .commit()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                RegisterFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}
