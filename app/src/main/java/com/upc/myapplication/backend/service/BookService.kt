package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.BookDao
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.BookFields

class BookService {
    companion object {
        fun getAvailableBooks(): ArrayList<Book> {
            val bookRecords: Array<AirtableRecord<BookFields>> = BookDao.getAvailableBooks()
            val books = arrayListOf<Book>()

            for (bookRecord in bookRecords) {
                val fields = bookRecord.fields
                val book = Book().apply {
                    title = fields.title
                    author = fields.author
                    genre = fields.genre
                    year = fields.year
                    coverUrl = fields.attachment?.getOrNull(0)?.url
                    stock = fields.stock
                    unavailable = fields.unavailable
                    available = fields.available
                }
                books.add(book)
            }

            return books
        }
    }
}