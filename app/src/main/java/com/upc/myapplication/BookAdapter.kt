package com.upc.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.myapplication.backend.model.Book

class BookAdapter(private var books: List<Book>,
                  private val onReserveClick: ((Book) -> Unit)?
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.bookImage)
        val title: TextView = itemView.findViewById(R.id.bookTitle)
        val author: TextView = itemView.findViewById(R.id.bookAuthor)
        val genre: TextView = itemView.findViewById(R.id.bookGenre)
        val year: TextView = itemView.findViewById(R.id.bookYear)
        val available: TextView = itemView.findViewById(R.id.bookAvailable)
        val reserveButton: Button = itemView.findViewById(R.id.reserveButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        //Carga datos basicos
        holder.title.text = book.title
        holder.author.text = "Autor: ${book.author}"
        holder.genre.text = "Genero: ${book.genre}"
        holder.year.text = "Año: ${book.year}"
        holder.available.text = "${book.available} unidades disponibles"

        //Carga la imagen
        Glide.with(holder.itemView).load(book.coverUrl).into(holder.image)

        //Config de Boton
        holder.reserveButton.setOnClickListener {
            onReserveClick?.invoke(book)
        }
    }

    fun updateData(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}

