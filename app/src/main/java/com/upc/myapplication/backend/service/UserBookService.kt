package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserBookDao
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.UserBook
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.UserBookFields
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import java.util.Date

class UserBookService {
    companion object {
        fun lendBook(userBook : UserBook){
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            format.timeZone = TimeZone.getTimeZone("UTC")

            val userBookFields = UserBookFields().apply {
                this.status = "Prestado"
                this.lendDate = format.format(Date())
            }
            val userBookRecord = AirtableRecord<UserBookFields>(userBookFields).apply {
                this.id = userBook.id
            }
            UserBookDao.updateUserBook(userBookRecord)
        }

        fun extendBookLoan(userBook: UserBook){
            val userBookFields = UserBookFields().apply {
                this.lendNumber = userBook.lendNumber + 1
            }
            val userBookRecord = AirtableRecord<UserBookFields>(userBookFields).apply {
                this.id = userBook.id
            }
            UserBookDao.updateUserBook(userBookRecord)
        }

        fun endBookLoan(userBook : UserBook){
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            format.timeZone = TimeZone.getTimeZone("UTC")

            val userBookFields = UserBookFields().apply {
                this.status = "Devuelto"
                this.returnedDate = format.format(Date())
            }
            val userBookRecord = AirtableRecord<UserBookFields>(userBookFields).apply {
                this.id = userBook.id
            }
            UserBookDao.updateUserBook(userBookRecord)
        }

        fun reserveBook(user: User, book: Book){
            val userBookFields = UserBookFields().apply {
                this.userRecordId = arrayOf(user.recordId)
                this.bookRecordId = arrayOf(book.recordId)
                this.status = "Reservado"
            }
            UserBookDao.registerUserBook(userBookFields)
        }

        fun getAllUserBooks() : ArrayList<UserBook>{
            val userBooks = arrayListOf<UserBook>()

            val userMap = UserService.getUsersWithBooks().associateBy { it.recordId }
            val bookMap = BookService.getBooksWithUsers().associateBy { it.recordId }
            val userBookRecords = UserBookDao.getAllUserBooks()
            for (userBookRecord in userBookRecords) {
                val userBookFields = userBookRecord.fields
                val userBook = UserBook().apply {
                    this.id = userBookRecord.id
                    this.user = userMap[userBookFields.userRecordId.first()]
                    this.book = bookMap[userBookFields.bookRecordId.first()]
                    this.status = userBookFields.status
                    this.lendNumber = userBookFields.lendNumber

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

        fun getUserBooks(user: User?) : ArrayList<UserBook>{
            val userBooks = arrayListOf<UserBook>()

            val bookMap = BookService.getBooksWithUsers().associateBy { it.recordId }

            val userBookRecords = UserBookDao.getUserBooks(user?.recordId)
            for (userBookRecord in userBookRecords) {
                val userBookFields = userBookRecord.fields
                val userBook = UserBook().apply {
                    this.id = userBookRecord.id
                    this.user = user
                    this.book = bookMap[userBookFields.bookRecordId.first()]
                    this.status = userBookFields.status
                    this.lendNumber = userBookFields.lendNumber

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