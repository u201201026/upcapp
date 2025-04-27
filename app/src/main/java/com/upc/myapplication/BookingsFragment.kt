package com.upc.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upc.myapplication.backend.model.UserBook
import com.upc.myapplication.backend.service.UserBookService
import com.upc.myapplication.backend.session.UserSession
import kotlin.concurrent.thread

class BookingsFragment : BaseFragment(R.layout.fragment_bookings) {
    private lateinit var adapter: UserBookAdapter
    private var fullUserBookList: List<UserBook> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializo el adaptador
        adapter = UserBookAdapter(emptyList())

        //Carga los libros asociados al usuario en pantalla
        val recyclerView = view.findViewById<RecyclerView>(R.id.userBookRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        loadUserBooks()

        //Configura la busqueda por titulo
        val searchView = view.findViewById<SearchView>(R.id.userBookSearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = if (newText.isNullOrBlank()) {
                    fullUserBookList
                } else {
                    fullUserBookList.filter {
                        it.book.title.contains(newText, ignoreCase = true)
                    }
                }
                adapter.updateData(filtered)
                return true
            }
        })
    }

    private fun loadUserBooks() {
        thread {
            val userBooks = UserBookService.getAllUserBooks(UserSession.currentUser)
            fullUserBookList = userBooks
            requireActivity().runOnUiThread {
                if (isAdded) {
                    adapter.updateData(fullUserBookList)
                }
            }
        }
    }
}