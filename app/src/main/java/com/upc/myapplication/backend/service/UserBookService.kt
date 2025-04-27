package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserBookDao
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.UserBook
import com.upc.myapplication.backend.model.airtable.UserBookFields
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class UserBookService {
    companion object {
        fun reserveBook(user: User, book: Book){
            val userBookFields = UserBookFields().apply {
                this.userRecordId = arrayOf(user.recordId)
                this.bookRecordId = arrayOf(book.recordId)
                this.status = "Reservado"
            }
            UserBookDao.registerUserBook(userBookFields)
        }

        fun getAllUserBooks(user: User?) : ArrayList<UserBook>{
            val userBooks = arrayListOf<UserBook>()

            val userBookRecords = UserBookDao.getAllUserBooks(user?.recordId)
            for (userBookRecord in userBookRecords) {
                val userBookFields = userBookRecord.fields
                val userBook = UserBook().apply {
                    this.user = user
                    this.book = BookService.getBookByRecordId(userBookFields.bookRecordId.first())
                    this.status = userBookFields.status
                    this.lendNumber = userBookFields.lendNumber?.toInt()

                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                    format.timeZone = TimeZone.getTimeZone("UTC")
                    this.reserveDueDate = userBookFields.reserveDueDate?.let { format.parse(it) }
                    this.lendDueDate = userBookFields.lendDueDate?.let { format.parse(it) }
                    this.returnedDate = userBookFields.returnedDate?.let { format.parse(it) }
                }
                userBooks.add(userBook)
            }

            return userBooks
        }
    }
}