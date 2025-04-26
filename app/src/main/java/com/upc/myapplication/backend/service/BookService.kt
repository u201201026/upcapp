package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.BookDao
import com.upc.myapplication.backend.dao.UserBookDao
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.User
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.BookFields
import com.upc.myapplication.backend.model.airtable.UserBookFields

class BookService {
    companion object {
        fun getAvailableBooks(): ArrayList<Book> {
            val books = arrayListOf<Book>()

            val bookRecords: Array<AirtableRecord<BookFields>> = BookDao.getAvailableBooks()
            for (bookRecord in bookRecords) {
                val fields = bookRecord.fields
                val book = Book().apply {
                    this.recordId = bookRecord.id
                    this.title = fields.title
                    this.author = fields.author
                    this.genre = fields.genre
                    this.year = fields.year
                    this.coverUrl = fields.attachment?.getOrNull(0)?.url
                    this.stock = fields.stock
                    this.unavailable = fields.unavailable
                    this.available = fields.available
                }
                books.add(book)
            }

            return books
        }

        fun reserveBook(user: User, book: Book){
            val userBookFields = UserBookFields().apply {
                this.user = arrayOf(user.recordId)
                this.book = arrayOf(book.recordId)
                this.status = "Reservado"
            }
            UserBookDao.registerUserBook(userBookFields)
        }
    }
}