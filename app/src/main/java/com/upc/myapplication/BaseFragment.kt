package com.upc.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.upc.myapplication.backend.session.UserSession

open class BaseFragment(@LayoutRes private val layoutResId: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val baseView = inflater.inflate(R.layout.fragment_base, container, false)

        val contentContainer = baseView.findViewById<FrameLayout>(R.id.contentContainer)
        val customContent = inflater.inflate(layoutResId, contentContainer, false)
        contentContainer.addView(customContent)

        val greetingTextView = baseView.findViewById<TextView>(R.id.tvGreeting)
        greetingTextView.text = "Hola, ${UserSession.currentUser?.fullName ?: "Invitado"}"

        return baseView
    }
}