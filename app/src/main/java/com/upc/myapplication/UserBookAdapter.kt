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
import com.upc.myapplication.backend.session.UserSession
import java.text.SimpleDateFormat
import java.util.Locale

class UserBookAdapter(private var userBooks: List<UserBook>,
                      private val onLendClick: ((UserBook) -> Unit)?,
                      private val onExtendClick: ((UserBook) -> Unit)?,
                      private val onEndLoanClick: ((UserBook) -> Unit)?
)  : RecyclerView.Adapter<UserBookAdapter.UserBookViewHolder>() {
    class UserBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.userBookImage)
        val title: TextView = itemView.findViewById(R.id.userBookTitle)
        val author: TextView = itemView.findViewById(R.id.userBookAuthor)
        val status: TextView = itemView.findViewById(R.id.userBookStatus)
        val extendButton: Button = itemView.findViewById(R.id.extendButton)
        val lendButton: Button = itemView.findViewById(R.id.lendButton)
        val endLoanButton: Button = itemView.findViewById(R.id.endLoanButton)
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

        //Fecha actual y formato
        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("America/Lima")).time
        val targetFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        targetFormat.timeZone = TimeZone.getTimeZone("America/Lima")

        //Configuración de detalles visuales de componenentes
        holder.status.setTextColor(Color.GRAY)
        holder.extendButton.visibility = View.GONE
        holder.lendButton.visibility = View.GONE
        holder.endLoanButton.visibility = View.GONE

        //Configuración de datos informativos
        var status = ""
        if(userBook.status == "Reservado"){
            if(currentDate.after(userBook.reserveDueDate)){
                status = "Reserva vencida."
            }
            else{
                status = "La reserva vence el " + targetFormat.format(userBook.reserveDueDate)
                if(UserSession.currentUser?.type != "Cliente"){
                    holder.lendButton.visibility = View.VISIBLE
                }
            }
        }
        else if(userBook.status == "Prestado"){
            //Solo el empleado puede ver el boton para finalizar el prestamo
            if(UserSession.currentUser?.type != "Cliente"){
                holder.endLoanButton.visibility = View.VISIBLE
            }
            //Configura los estados del prestamo
            if(currentDate.after(userBook.lendDueDate)){
                status = "Prestamo vencido el ${targetFormat.format(userBook.lendDueDate)}"
                holder.status.setTextColor(Color.RED)
            }
            else{
                status = "El prestamo vence el " + targetFormat.format(userBook.lendDueDate)
                if(userBook.lendNumber < 3) {
                    if(UserSession.currentUser?.type == "Cliente"){
                        holder.extendButton.visibility = View.VISIBLE
                    }
                }
                else{
                    status = status + "\nAlcanzó el límite máximo de extensiones de Préstamo"
                }
            }
        }
        else{
            status = "Prestamo finalizado."
        }
        if(UserSession.currentUser?.type != "Cliente"){
            status = "Cliente ${userBook.user.fullName} \n${status}"
        }
        holder.status.text = status

        //Carga la imagen
        Glide.with(holder.itemView).load(userBook.book.coverUrl).into(holder.image)

        //Config de Boton
        holder.lendButton.setOnClickListener {
            onLendClick?.invoke(userBook)
        }
        holder.extendButton.setOnClickListener {
            onExtendClick?.invoke(userBook)
        }
        holder.endLoanButton.setOnClickListener {
            onEndLoanClick?.invoke(userBook)
        }
    }

    fun updateData(newUserBooks: List<UserBook>) {
        userBooks = newUserBooks
        notifyDataSetChanged()
    }
}