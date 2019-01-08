package com.example.mauricioliveira.kotlinapp.privates.mainmenu

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mauricioliveira.kotlinapp.BaseFragment
import com.example.mauricioliveira.kotlinapp.R
import com.example.mauricioliveira.kotlinapp.adapters.CategoryAdapter
import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetAmountTaskListener
import com.example.mauricioliveira.kotlinapp.communication.model.ReturnData
import com.example.mauricioliveira.kotlinapp.communication.task.GetAmountTask
import com.example.mauricioliveira.kotlinapp.database.PersistentData
import com.example.mauricioliveira.kotlinapp.firebase.FirebaseAuthBase
import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory
import com.example.mauricioliveira.kotlinapp.privates.quiz.QuizFragment
import com.example.mauricioliveira.kotlinapp.public.login.LoginFragment
import com.google.firebase.auth.FirebaseUser


class MainMenuFragment : BaseFragment(), MainMenuViewInterface, GetAmountTaskListener {

    private lateinit var mPresenter: MainMenuFragmentPresenter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var logoutButton: Button
    private var fbAuthBase = FirebaseAuthBase()
    private lateinit var categoryName: String
    private lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        linearLayoutManager = LinearLayoutManager(mContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_main_menu, container, false)

        mRecyclerView = mRootView.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = linearLayoutManager

        logoutButton = mRootView.findViewById(R.id.logout_button)

        logoutButton.setOnClickListener { logout() }

        mPresenter = MainMenuFragmentPresenter(this)

        this.callServices()
        return mRootView
    }

    private fun logout() {
        this.fbAuthBase.authInstance!!.signOut()

        var fragment = LoginFragment.newInstance()

        this.fragmentManager!!.beginTransaction()
                .replace(R.id.main_fl, fragment)
                .commit()
    }

    private fun callServices() {
        this.mPresenter.getApiToken()
        this.mPresenter.getCategories()
    }

    override fun fillCategoryList(categoriesList: ArrayList<TriviaCategory>) {
        var adapter = CategoryAdapter(categoriesList, this)
        mRecyclerView.adapter = adapter
    }

    override fun onSelectCategory(triviaCategory: TriviaCategory) {
        var category = triviaCategory.id
        this.categoryName = triviaCategory.name
        var token = PersistentData.instance.apiToken

        var amountTask = GetAmountTask(this)
        amountTask.getAmount(category,token)
    }


    override fun onGetAmountTaskSuccess(returnData: ReturnData) {
        returnData?.let {

            var fragment = QuizFragment.newInstance(it)

            this.fragmentManager!!.beginTransaction()
                    .replace(R.id.main_fl,fragment)
                    .commit()

        }
    }

    override fun onGetAmountTaskUnSucess(error: Throwable) {
    }

    

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onResume() {
        super.onResume()
        this.setToolbarTitle("Category Choice")
        this.setDisplayHome(false)
    }

    companion object {
        @JvmStatic
        fun newInstance(currentUser: FirebaseUser?) =
                MainMenuFragment().apply {
                    arguments = Bundle().apply {
                    }
                }

        fun newInstance() = MainMenuFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}
