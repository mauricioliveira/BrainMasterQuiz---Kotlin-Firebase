package com.example.mauricioliveira.kotlinapp.privates.mainmenu

import com.example.mauricioliveira.kotlinapp.communication.model.ApiToken
import com.example.mauricioliveira.kotlinapp.database.PersistentData
import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory
import com.google.firebase.database.DatabaseError

class MainMenuFragmentPresenter (menuView: MainMenuViewInterface) : MainMenuPresenterInterface.ApiTokenTaskInterface, MainMenuPresenterInterface.CategoriesTaskInterface {

    private var mMenuView = menuView
    private var mainMenuInteractor: MainMenuPresenterInterface = MainMenuCallService()

    fun getApiToken() {
        mainMenuInteractor.getApiToken(this)
    }

    fun getCategories() {
        mainMenuInteractor.getCategories(this)
    }

    override fun onApiTokenSuccess(apiToken: ApiToken) {
        PersistentData.instance.apiToken = apiToken.token
    }

    override fun onApiTokenFailure(error: Throwable) {
    }

    override fun onCategoriesTaskSuccess(categoriesList: ArrayList<TriviaCategory>) {
       mMenuView.fillCategoryList(categoriesList)
    }

    override fun onCategoriesTaskFailure(error: DatabaseError) {
    }
}