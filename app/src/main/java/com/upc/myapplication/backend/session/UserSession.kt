package com.upc.myapplication.backend.session

import com.upc.myapplication.backend.model.User

object UserSession {
    var currentUser: User? = null

    fun clear() {
        currentUser = null
    }
}