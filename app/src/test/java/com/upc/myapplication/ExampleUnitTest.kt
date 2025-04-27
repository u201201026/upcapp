package com.upc.myapplication

import com.upc.myapplication.backend.dao.BookDao
import com.upc.myapplication.backend.dao.UserBookDao
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.service.UserBookService
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        /*
        //Obtiene todos los libros asociados a un usuario
        var user = User()
        user.recordId = "reckvsY8SMrqSmkHL"
        val userBooks = UserBookService.getAllUserBooks(user)
        */

        assertEquals(4, 2 + 2)
    }
}