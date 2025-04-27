package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.BookDao
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.model.airtable.AirtableRecord
import com.upc.myapplication.backend.model.airtable.BookFields

class BookService {
    companion object {
        fun getAllBooks(): ArrayList<Book> {
            val books = arrayListOf<Book>()

            val bookRecords: Array<AirtableRecord<BookFields>> = BookDao.getAllBooks()
            for (bookRecord in bookRecords) {
                val fields = bookRecord.fields
                val book = Book().apply {
                    this.recordId = bookRecord.id
                    this.title = fields.title
                    this.author = fields.author
                    this.genre = fields.genre
                    this.year = fields.year
                    this.coverUrl = fields.attachment?.getOrNull(0)?.url
                    this.stock = fields.stock.toInt()
                    this.unavailable = fields.unavailable.toInt()
                    this.available = fields.available.toInt()
                }
                books.add(book)
            }

            return books
        }

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
                    this.stock = fields.stock.toInt()
                    this.unavailable = fields.unavailable.toInt()
                    this.available = fields.available.toInt()
                }
                books.add(book)
            }

            return books
        }

        fun getBookByRecordId(recordId : String) : Book{
            val bookRecord = BookDao.getByRecordId(recordId)
            val fields = bookRecord.fields
            val book = Book().apply {
                this.recordId = bookRecord.id
                this.title = fields.title
                this.author = fields.author
                this.genre = fields.genre
                this.year = fields.year
                this.coverUrl = fields.attachment?.getOrNull(0)?.url
                this.stock = fields.stock.toInt()
                this.unavailable = fields.unavailable.toInt()
                this.available = fields.available.toInt()
            }
            return book
        }

        fun getBooksWithUsers() : ArrayList<Book>{
            val books = arrayListOf<Book>()

            val bookRecords: Array<AirtableRecord<BookFields>> = BookDao.getBooksWithUsers()
            for (bookRecord in bookRecords) {
                val fields = bookRecord.fields
                val book = Book().apply {
                    this.recordId = bookRecord.id
                    this.title = fields.title
                    this.author = fields.author
                    this.genre = fields.genre
                    this.year = fields.year
                    this.coverUrl = fields.attachment?.getOrNull(0)?.url
                    this.stock = fields.stock.toInt()
                    this.unavailable = fields.unavailable.toInt()
                    this.available = fields.available.toInt()
                }
                books.add(book)
            }

            return books
        }
    }
}