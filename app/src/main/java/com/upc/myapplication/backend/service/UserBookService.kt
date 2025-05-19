package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.UserBookApi
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.ReserveBookRequest
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.UserBook
import java.util.TimeZone
import java.util.Date

class UserBookService {
    companion object {
        fun lendBook(userBook : UserBook){
            UserBookApi.lendBook(userBook.id)
        }

        fun extendLoan(userBook: UserBook){
            UserBookApi.extendLoan(userBook.id)
        }

        fun returnBook(userBook : UserBook){
            UserBookApi.returnBook(userBook.id)
        }

        fun reserveBook(user: User, book: Book){
            val request = ReserveBookRequest().apply {
                this.userRecordId = user.recordId
                this.bookRecordId = book.recordId
            }
            UserBookApi.registerUserBook(request)
        }

        fun getAllUserBooks() : Array<UserBook>{
            val userMap = UserService.getBookLinkedUsers().associateBy { it.recordId }
            val bookMap = BookService.getUserLinkedBooks().associateBy { it.recordId }
            val userBooks = UserBookApi.getAllUserBooks()
            for (userBook in userBooks) {
                userBook.user = userMap[userBook.userRecordId]
                userBook.book = bookMap[userBook.bookRecordId]

                userBook.reserveDueDate = convertUtcToLima(userBook.reserveDueDate)
                userBook.lendDueDate = convertUtcToLima(userBook.lendDueDate)
                userBook.returnedDate = convertUtcToLima(userBook.returnedDate)
            }
            return userBooks
        }

        fun getUserBooksByUserRecordId(userRecordId : String?) : Array<UserBook>{
            val bookMap = BookService.getUserLinkedBooks().associateBy { it.recordId }
            val userBooks = UserBookApi.getUserBooksByUserRecordId(userRecordId)
            for (userBook in userBooks) {
                userBook.book = bookMap[userBook.bookRecordId]

                userBook.reserveDueDate = convertUtcToLima(userBook.reserveDueDate)
                userBook.lendDueDate = convertUtcToLima(userBook.lendDueDate)
                userBook.returnedDate = convertUtcToLima(userBook.returnedDate)
            }
            return userBooks
        }

        fun convertUtcToLima(date: Date?): Date? {
            return date?.let {
                val offsetMillis = TimeZone.getTimeZone("America/Lima").getOffset(it.time)
                Date(it.time + offsetMillis)
            }
        }
    }
}