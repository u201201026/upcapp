package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserDao
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.UserFields

class UserService {
    companion object {
        fun authenticateUser(id : String, password : String): User? {
            val userRecords: Array<AirtableRecord<UserFields>> = UserDao.getUserByIdAndPassword(id, password)
            val userRecord = userRecords.firstOrNull()
            return userRecord?.let {
                User().apply {
                    dni = it.fields.id
                    email = it.fields.email
                    fullName = it.fields.fullName
                    type = it.fields.type
                }
            }
        }
    }
}