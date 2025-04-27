package com.upc.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.myapplication.backend.model.Book
import com.upc.myapplication.backend.service.BookService
import com.upc.myapplication.backend.service.UserBookService
import com.upc.myapplication.backend.session.UserSession
import kotlin.concurrent.thread

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var adapter: BookAdapter
    private var fullBookList: List<Book> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        searchView.queryHint = "Título"

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
    }

    private fun loadBooks() {
        thread {
            val books = if (UserSession.currentUser?.type == "Cliente") {
                BookService.getAvailableBooks()
            } else {
                BookService.getAllBooks()
            }
            fullBookList = books
            requireActivity().runOnUiThread {
                if (isAdded) {
                    adapter.updateData(books)
                }
            }
        }
    }

    private fun onReserveClick(book: Book) {
        if(UserSession.currentUser == null){
            Toast.makeText(requireContext(), "Debes iniciar sesión para reservar el libro!", Toast.LENGTH_SHORT).show()
        }
        else{
            thread {
                UserBookService.reserveBook(UserSession.currentUser!!, book)
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Libro reservado!", Toast.LENGTH_SHORT).show()
                    //Refrescamos la lista de libros porque la cantidad de libros disponibles ha cambiado
                    loadBooks()
                }
            }
        }
    }
}