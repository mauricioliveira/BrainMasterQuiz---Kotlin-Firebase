package com.example.mauricioliveira.kotlinapp.utils

import android.util.Base64

class Util {
    companion object {
        fun decode64toString(string: String) : String {
            var decode = Base64.decode(string,android.util.Base64.DEFAULT)

            return String(decode)
        }

        fun listDecode64toString (list: List<String>) : List<String> {
            var returnList : List<String> = listOf()

            for (i in list.indices) {
                returnList += decode64toString(list[i])
            }

            return returnList
        }
    }
}