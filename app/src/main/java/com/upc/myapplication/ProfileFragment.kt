package com.upc.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView

class ProfileFragment : BaseFragment(R.layout.fragment_profile){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.messageTextView).text = "Perfil"
    }
}