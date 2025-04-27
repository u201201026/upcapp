package com.upc.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.TimeZone
import com.upc.myapplication.backend.model.UserBook
import java.util.Calendar
import android.graphics.Color
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Locale

class UserBookAdapter(private var userBooks: List<UserBook>)  : RecyclerView.Adapter<UserBookAdapter.UserBookViewHolder>() {
    class UserBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.userBookImage)
        val title: TextView = itemView.findViewById(R.id.userBookTitle)
        val author: TextView = itemView.findViewById(R.id.userBookAuthor)
        val status: TextView = itemView.findViewById(R.id.userBookStatus)
        val extendButton: Button = itemView.findViewById(R.id.extendButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userbook, parent, false)
        return UserBookViewHolder(view)
    }

    override fun getItemCount(): Int = userBooks.size

    override fun onBindViewHolder(holder: UserBookViewHolder, position: Int) {
        val userBook = userBooks[position]

        //Carga datos basicos
        holder.title.text = userBook.book.title
        holder.author.text = "Autor: ${userBook.book.author}"

        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("America/Lima")).time
        val targetFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        targetFormat.timeZone = TimeZone.getTimeZone("America/Lima")

        var status = ""
        holder.extendButton.visibility = View.GONE
        if(userBook.status == "Reservado"){
            if(currentDate.after(userBook.reserveDueDate)){
                holder.status.text = "Reserva vencida."
            }
            else{
                holder.status.text = "Su reserva vence el " + targetFormat.format(userBook.reserveDueDate)
            }
        }
        else if(userBook.status == "Prestado"){
            if(currentDate.after(userBook.lendDueDate)){
                holder.status.text = "Prestamo vencido."
                holder.status.setTextColor(Color.RED)
            }
            else{
                val lendText = "Su prestamo vence el " + targetFormat.format(userBook.lendDueDate)
                holder.status.text = lendText
                if(userBook.lendNumber < 3) {
                    holder.extendButton.visibility = View.VISIBLE
                }
                else{
                    holder.status.text = lendText + "\nAlcanzó el límite máximo de extensiones de Préstamo"
                }
            }
        }
        else{
            holder.status.text = "Prestamo finalizado."
        }

        //Carga la imagen
        Glide.with(holder.itemView).load(userBook.book.coverUrl).into(holder.image)
    }

    fun updateData(newUserBooks: List<UserBook>) {
        userBooks = newUserBooks
        notifyDataSetChanged()
    }
}