package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserApi
import com.upc.myapplication.backend.model.AuthRequest
import com.upc.myapplication.backend.model.CreateUserRequest
import com.upc.myapplication.backend.model.User

class UserService {
    companion object {
        fun authenticate(nationalId : String, password : String): User? {
            val request = AuthRequest().apply {
                this.nationalId = nationalId
                this.password = password
            }
            return UserApi.authenticate(request)
        }

        fun registerUser(id : String, email : String, fullName : String, password : String, type : String) : User? {
            val request = CreateUserRequest().apply {
                this.nationalId = id
                this.email = email
                this.fullName = fullName
                this.password = password
                this.type = type
            }
            return UserApi.registerUser(request)
        }

        fun getUserByRecordId(recordId : String) : User{
            return  UserApi.getByRecordId(recordId)
        }

        fun getBookLinkedUsers() : Array<User>{
            return UserApi.getBookLinkedUsers()
        }
    }
}