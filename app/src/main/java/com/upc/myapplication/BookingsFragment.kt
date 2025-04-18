package com.upc.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookingsFragment : Fragment(R.layout.fragment_bookings) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bookings, container, false)
        view.findViewById<TextView>(R.id.messageTextView).text = "Tu Reserva"
        return view
    }
}