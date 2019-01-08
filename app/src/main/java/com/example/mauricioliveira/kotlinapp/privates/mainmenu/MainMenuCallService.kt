package com.example.mauricioliveira.kotlinapp.privates.mainmenu

import com.example.mauricioliveira.kotlinapp.communication.interfaces.GetApiTokenTaskListener
import com.example.mauricioliveira.kotlinapp.communication.model.ApiToken
import com.example.mauricioliveira.kotlinapp.communication.task.GetApiTokenTask
import com.example.mauricioliveira.kotlinapp.firebase.FirebaseDatabaseBase
import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainMenuCallService : MainMenuPresenterInterface, GetApiTokenTaskListener {

    private lateinit var apiTokenInterface: MainMenuPresenterInterface.ApiTokenTaskInterface
    private lateinit var categoriesInterface: MainMenuPresenterInterface.CategoriesTaskInterface
    private var categoriesList: ArrayList<TriviaCategory> = arrayListOf()

    override fun getApiToken(listener: MainMenuPresenterInterface.ApiTokenTaskInterface) {
        var apiTask = GetApiTokenTask(this)

        apiTokenInterface = listener

        apiTask.getApiToken()
    }

    override fun getCategories(listener: MainMenuPresenterInterface.CategoriesTaskInterface) {
        categoriesInterface = listener

        FirebaseDatabaseBase.triviaCategories.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                categoriesInterface.onCategoriesTaskFailure(error)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var children = snapshot.children

                children.forEach {
                    var triviaCategory = TriviaCategory()
                    triviaCategory.id = it.child("id").value as String
                    triviaCategory.name = it.child("name").value as String

                    categoriesList.add(triviaCategory)
                }

                categoriesInterface.onCategoriesTaskSuccess(categoriesList)
            }
        })
    }

    override fun onGetApiTokenTaskSuccess(apiToken: ApiToken) {
        apiTokenInterface.onApiTokenSuccess(apiToken)
    }

    override fun onGetApiTokenTaskUnSucess(error: Throwable) {
        apiTokenInterface.onApiTokenFailure(error)
    }

}