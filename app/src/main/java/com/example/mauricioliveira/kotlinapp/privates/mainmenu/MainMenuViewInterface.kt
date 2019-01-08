package com.example.mauricioliveira.kotlinapp.privates.mainmenu

import com.example.mauricioliveira.kotlinapp.objects.TriviaCategory

interface MainMenuViewInterface {
    fun showLoading()
    fun hideLoading()
    fun fillCategoryList(categoriesList: ArrayList<TriviaCategory>)
    fun onSelectCategory(triviaCategory: TriviaCategory)
}