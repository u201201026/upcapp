package com.upc.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.StyleSpan

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configCreateAccountLink()
    }

    fun configCreateAccountLink() {
        val fullText = "¿No tienes una cuenta? Crea una"
        val startIndex = fullText.indexOf("Crea una")
        val endIndex = fullText.length

        //Configura la porción de texto que se usará como enlace
        val spannable = SpannableString(fullText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = Color.GRAY
                ds.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
        }
        spannable.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        //Aplica la configuración en el control
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        tvCreateAccount.text = spannable
        tvCreateAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    fun login(view: View) {
        val dni = findViewById<TextView>(R.id.etDni).text.toString().trim()
        val password = findViewById<TextView>(R.id.etPassword).text.toString().trim()
        if (dni.isEmpty()) {
            Toast.makeText(this, "El DNI es obligatorio", Toast.LENGTH_SHORT).show()
        }
        else if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña es obligatoria", Toast.LENGTH_SHORT).show()
        }
        else{
            //TODO: Validación de usuario
            if (dni == "1234" && password == "1234") {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "DNI o Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }

    }
}