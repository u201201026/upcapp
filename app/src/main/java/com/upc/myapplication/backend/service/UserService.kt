package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserDao
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.UserFields

class UserService {
    companion object {
        fun authenticate(id : String, password : String): User? {
            val userRecords: Array<AirtableRecord<UserFields>> = UserDao.getUserByIdAndPassword(id, password)
            val userRecord = userRecords.firstOrNull()
            return userRecord?.let {
                User().apply {
                    this.dni = it.fields.id
                    this.email = it.fields.email
                    this.fullName = it.fields.fullName
                    this.type = it.fields.type
                }
            }
        }

        fun createAccount(dni : String, email : String, fullName : String, password : String, type : String) : User? {
            val userFields = UserFields().apply {
                this.id = dni
                this.email = email
                this.fullName = fullName
                this.password = password
                this.type = type
            }
            val userRecord : AirtableRecord<UserFields> = UserDao.create(userFields)
            return userRecord.let {
                User().apply {
                    this.dni = it.fields.id
                    this.email = it.fields.email
                    this.fullName = it.fields.fullName
                    this.type = it.fields.type
                }
            }
        }
    }
}