package com.upc.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserBookAdapter(
            emptyList(),
            onLendClick = { userBook -> onLendClick(userBook) },
            onExtendClick = { userBook -> onExtendClick(userBook) },
            onEndLoanClick = { userBook -> onEndLoanClick(userBook) },
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.userBookRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchView = view.findViewById(R.id.userBookSearchView)
        if (UserSession.currentUser?.type == "Cliente") {
            searchView.queryHint = "Título"
        } else {
            searchView.queryHint = "DNI del cliente"
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                applyFilter(newText ?: "")
                return true
            }
        })

        loadUserBooks()
    }

    private fun applyFilter(query: String) {
        val filtered = if (query.isBlank()) {
            fullUserBookList
        } else {
            if (UserSession.currentUser?.type == "Cliente") {
                fullUserBookList.filter {
                    it.book.title.contains(query, ignoreCase = true)
                }
            } else {
                fullUserBookList.filter {
                    it.user.nationalId.contains(query, ignoreCase = true)
                }
            }
        }
        adapter.updateData(filtered)
    }

    private fun loadUserBooks() {
        thread {
            val userBooks = if (UserSession.currentUser?.type == "Cliente") {
                UserBookService.getUserBooksByUserRecordId(UserSession.currentUser?.recordId)
            } else {
                UserBookService.getAllUserBooks()
            }
            fullUserBookList = userBooks.toList()
            requireActivity().runOnUiThread {
                if (isAdded) {
                    applyFilter(searchView.query?.toString() ?: "")
                }
            }
        }
    }

    private fun onLendClick(userBook: UserBook) {
        if (UserSession.currentUser == null) {
            Toast.makeText(requireContext(), "Debes iniciar sesión para prestar el libro!", Toast.LENGTH_SHORT).show()
        } else {
            thread {
                UserBookService.lendBook(userBook)
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Libro prestado!", Toast.LENGTH_SHORT).show()
                    loadUserBooks()
                }
            }
        }
    }

    private fun onExtendClick(userBook: UserBook) {
        if (UserSession.currentUser == null) {
            Toast.makeText(requireContext(), "Debes iniciar sesión para extender el préstamo del libro!", Toast.LENGTH_SHORT).show()
        } else {
            thread {
                UserBookService.extendLoan(userBook)
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Préstamo extendido!", Toast.LENGTH_SHORT).show()
                    loadUserBooks()
                }
            }
        }
    }

    private fun onEndLoanClick(userBook: UserBook) {
        if (UserSession.currentUser == null) {
            Toast.makeText(requireContext(), "Debes iniciar sesión para finalizar el préstamo del libro!", Toast.LENGTH_SHORT).show()
        } else {
            thread {
                UserBookService.returnBook(userBook)
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Préstamo finalizado!", Toast.LENGTH_SHORT).show()
                    loadUserBooks()
                }
            }
        }
    }
}