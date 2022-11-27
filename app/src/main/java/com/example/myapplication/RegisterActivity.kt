package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginText: TextView = findViewById(R.id.textView_login_now)
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.button_register)
        registerButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val username: EditText = findViewById(R.id.editText_username_register)
        val password: EditText = findViewById(R.id.editText_password_register)

        val inputUsername = username.text.toString()
        val inputPassword = password.text.toString()

        if (username.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill username and password fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(inputUsername, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Register failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error: ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}