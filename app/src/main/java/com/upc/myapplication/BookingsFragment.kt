package com.upc.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView

class BookingsFragment : BaseFragment(R.layout.fragment_bookings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.messageTextView).text = "Tus reservas y préstamos"
    }
}