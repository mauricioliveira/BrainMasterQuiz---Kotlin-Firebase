package com.example.mauricioliveira.kotlinapp.firebase

import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseBase {
    companion object {
        @JvmStatic
        var databaseInstance = FirebaseDatabase.getInstance()
        var usersReference = databaseInstance.getReference("users")
        var triviaCategories = databaseInstance.getReference("trivia_categories")
    }
}