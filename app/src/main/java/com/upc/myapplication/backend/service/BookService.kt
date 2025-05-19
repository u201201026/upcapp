package com.upc.myapplication.backend.service

import com.upc.myapplication.backend.dao.BookApi
import com.upc.myapplication.backend.model.Book

class BookService {
    companion object {
        fun getAllBooks(): Array<Book> {
            return BookApi.getAllBooks()
        }

        fun getAvailableBooks(): Array<Book> {
            return BookApi.getAvailableBooks()
        }

        fun getBookByRecordId(recordId : String) : Book{
            return BookApi.getByRecordId(recordId)
        }

        fun getUserLinkedBooks() : Array<Book>{
            return BookApi.getUserLinkedBooks()
        }
    }
}