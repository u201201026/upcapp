package com.upc.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.upc.myapplication.backend.session.UserSession

class ProfileFragment : BaseFragment(R.layout.fragment_profile){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changePasswordButton = view.findViewById<Button>(R.id.changePasswordButton)
        changePasswordButton.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            UserSession.clear()

            // Navegar al LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}