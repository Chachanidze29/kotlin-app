package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        val registerText: TextView = findViewById(R.id.textView_register_now)
        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.button_login)
        loginButton.setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        val username: EditText = findViewById(R.id.editText_username_login)
        val password: EditText = findViewById(R.id.editText_password_login)

        if (username.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill username and password fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val usernameInput = username.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(usernameInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}