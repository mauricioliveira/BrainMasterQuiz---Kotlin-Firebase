package com.example.mauricioliveira.kotlinapp.privates.mainmenu

import com.example.mauricioliveira.kotlinapp.communication.model.ApiToken
import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory
import com.google.firebase.database.DatabaseError

interface MainMenuPresenterInterface {
    fun getApiToken (listener: ApiTokenTaskInterface)

    fun getCategories (listener: CategoriesTaskInterface)

    interface ApiTokenTaskInterface {
        fun onApiTokenSuccess(apiToken: ApiToken)
        fun onApiTokenFailure(error: Throwable)
    }

    interface CategoriesTaskInterface {
        fun onCategoriesTaskSuccess(categoriesList: ArrayList<TriviaCategory>)
        fun onCategoriesTaskFailure(error: DatabaseError)
    }
}