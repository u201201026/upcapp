package com.upc.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.upc.myapplication.backend.session.UserSession

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var layoutStep1: LinearLayout
    private lateinit var layoutStep2: LinearLayout
    private lateinit var layoutStep3: LinearLayout
    private lateinit var layoutStep4: LinearLayout

    private lateinit var etEmail: EditText
    private lateinit var etCode: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var etRepeatPassword: EditText

    private lateinit var btnSendCode: Button
    private lateinit var btnVerifyCode: Button
    private lateinit var btnChangePassword: Button
    private lateinit var btnGoToLogin: Button

    private val mockCode = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_password)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        layoutStep1 = findViewById(R.id.layoutStep1)
        layoutStep2 = findViewById(R.id.layoutStep2)
        layoutStep3 = findViewById(R.id.layoutStep3)
        layoutStep4 = findViewById(R.id.layoutStep4)

        etEmail = findViewById(R.id.etEmail)
        etCode = findViewById(R.id.etCode)
        etNewPassword = findViewById(R.id.etNewPassword)
        etRepeatPassword = findViewById(R.id.etRepeatPassword)

        btnSendCode = findViewById(R.id.btnSendCode)
        btnVerifyCode = findViewById(R.id.btnVerifyCode)
        btnChangePassword = findViewById(R.id.btnChangePassword)
        btnGoToLogin = findViewById(R.id.btnGoToLogin)

        // Acciones de botones
        btnSendCode.setOnClickListener { enviarCodigo() }
        btnVerifyCode.setOnClickListener { verificarCodigo() }
        btnChangePassword.setOnClickListener { cambiarContrasena() }
        btnGoToLogin.setOnClickListener { irAlLogin() }

        if(UserSession.currentUser != null){
            val tvTitle = findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = "Cambiar Contraseña"
            cambiarPaso(3)
        }
    }

    private fun enviarCodigo() {
        val email = etEmail.text.toString().trim()
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Ingresa un correo válido"
            return
        }

        Toast.makeText(this, "Código enviado a $email", Toast.LENGTH_SHORT).show()
        cambiarPaso(2)
    }

    private fun verificarCodigo() {
        val code = etCode.text.toString().trim()
        if (code == mockCode) {
            cambiarPaso(3)
        } else {
            etCode.error = "Código incorrecto"
        }
    }

    private fun cambiarContrasena() {
        val pass = etNewPassword.text.toString()
        val repeat = etRepeatPassword.text.toString()

        if (pass.length < 8) {
            etNewPassword.error = "Mínimo 8 caracteres"
            return
        }

        if (pass != repeat) {
            etRepeatPassword.error = "Las contraseñas no coinciden"
            return
        }

        Toast.makeText(this, "Contraseña cambiada", Toast.LENGTH_SHORT).show()

        if(UserSession.currentUser == null){
            cambiarPaso(4)
        }
        else{
            finish()
        }
    }

    private fun irAlLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun cambiarPaso(paso: Int) {
        layoutStep1.visibility = if (paso == 1) View.VISIBLE else View.GONE
        layoutStep2.visibility = if (paso == 2) View.VISIBLE else View.GONE
        layoutStep3.visibility = if (paso == 3) View.VISIBLE else View.GONE
        layoutStep4.visibility = if (paso == 4) View.VISIBLE else View.GONE
    }
}
