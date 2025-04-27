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
                    this.recordId = it.id
                    this.dni = it.fields.id
                    this.email = it.fields.email
                    this.fullName = it.fields.fullName
                    this.type = it.fields.type
                }
            }
        }

        fun createAccount(id : String, email : String, fullName : String, password : String, type : String) : User? {
            val userFields = UserFields().apply {
                this.id = id
                this.email = email
                this.fullName = fullName
                this.password = password
                this.type = type
            }
            val userRecord : AirtableRecord<UserFields> = UserDao.registerUser(userFields)
            return userRecord.let {
                User().apply {
                    this.recordId = it.id
                    this.dni = it.fields.id
                    this.email = it.fields.email
                    this.fullName = it.fields.fullName
                    this.type = it.fields.type
                }
            }
        }

        fun getUserByRecordId(recordId : String) : User{
            val userRecord = UserDao.getByRecordId(recordId)
            return userRecord.let {
                User().apply {
                    this.recordId = it.id
                    this.dni = it.fields.id
                    this.email = it.fields.email
                    this.fullName = it.fields.fullName
                    this.type = it.fields.type
                }
            }
        }

        fun getUsersWithBooks() : ArrayList<User>{
            val users = arrayListOf<User>()

            val userRecords: Array<AirtableRecord<UserFields>> = UserDao.getUsersWithBooks()
            for (userRecord in userRecords) {
                val fields = userRecord.fields
                val user = User().apply {
                    this.recordId = userRecord.id
                    this.dni = fields.id
                    this.email = fields.email
                    this.fullName = fields.fullName
                    this.type = fields.type
                }
                users.add(user)
            }

            return users
        }
    }
}