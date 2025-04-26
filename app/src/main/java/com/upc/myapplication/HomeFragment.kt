package com.upc.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.service.BookService
import kotlin.concurrent.thread

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var adapter: BookAdapter
    private var fullBookList: List<Book> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //Inicializo el adaptador
        adapter = BookAdapter(emptyList()) { book ->
            //Configura la función que se ejecutara cada vez que se hace click en el botón de reserva
            onReserveClick(book)
        }

        //Carga los libros en pantalla
        val recyclerView = view.findViewById<RecyclerView>(R.id.bookRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        loadBooks()

        //Configura la busqueda por titulo
        val searchView = view.findViewById<SearchView>(R.id.bookSearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = if (newText.isNullOrBlank()) {
                    fullBookList
                } else {
                    fullBookList.filter {
                        it.title.contains(newText, ignoreCase = true)
                    }
                }
                adapter.updateData(filtered)
                return true
            }
        })

        return view
    }

    private fun loadBooks() {
        thread {
            val books = BookService.getAvailableBooks()
            fullBookList = books
            requireActivity().runOnUiThread {
                adapter.updateData(books)
            }
        }
    }

    private fun onReserveClick(book: Book) {
        //TODO: Registrar la reserva del libro en BD
        //Refrescamos la lista de libros porque la cantidad de libros disponibles ha cambiado
        loadBooks()
        Toast.makeText(requireContext(), "Libro reservado.", Toast.LENGTH_SHORT).show()
    }
}