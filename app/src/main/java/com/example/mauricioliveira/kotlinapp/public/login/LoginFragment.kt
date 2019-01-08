package com.example.mauricioliveira.kotlinapp.public.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mauricioliveira.kotlinapp.BaseFragment
import com.example.mauricioliveira.kotlinapp.controller.LoginInput
import com.example.mauricioliveira.kotlinapp.R
import com.example.mauricioliveira.kotlinapp.privates.mainmenu.MainMenuFragment
import com.example.mauricioliveira.kotlinapp.public.register.RegisterFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : BaseFragment(), View.OnClickListener, LoginViewInterface {

    private lateinit var username: LoginInput
    private lateinit var password: LoginInput
    private lateinit var loginButton: Button
    private lateinit var register: TextView
    private lateinit var mPresenter: LoginFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var firebaseUser = FirebaseAuth.getInstance().currentUser

        firebaseUser?.let { goToPrivate(firebaseUser) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        this.username = view.findViewById(R.id.username_input)
        this.password = view.findViewById(R.id.password_input)
        this.loginButton = view.findViewById(R.id.loginButton)
        this.register = view.findViewById(R.id.registerTextView)

        this.loginButton.setOnClickListener(this)
        this.register.setOnClickListener(this)

        this.mPresenter = LoginFragmentPresenter(this)

        return view
    }

    override fun onClick(p0: View?) {
        when (p0) {
            this.loginButton -> mPresenter.doLogin(this.username.getEditText()?.text.toString(), this.password.getEditText()?.text.toString())
            this.register -> mPresenter.doRegister()
        }
    }

    override fun goToRegister() {
        var fragment = RegisterFragment.newInstance()
        this.fragmentManager!!.beginTransaction()
                .addToBackStack(fragment.tag)
                .replace(R.id.main_fl, fragment)
                .commit()
    }

    override fun goToPrivate(currentUser: FirebaseUser?) {
        var fragment = MainMenuFragment.newInstance(currentUser)
        this.fragmentManager!!.beginTransaction()
                .replace(R.id.main_fl, fragment)
                .commit()
    }

    override fun showLoginFailure() {
        Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_LONG).show()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onResume() {
        super.onResume()
        this.setToolbarTitle("Login")
        this.setDisplayHome(false)
    }

    override fun onDetach() {
        super.onDetach()
        this.mPresenter.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}
