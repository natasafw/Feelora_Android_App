package com.example.feelora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DaftarActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val konfirmasipswd: EditText = findViewById(R.id.konfirmasipswd)
        val btndaftar: Button = findViewById(R.id.btndaftar)
        val masuk: TextView = findViewById(R.id.masuk)
        val sharPref: SharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE)

        btndaftar.setOnClickListener {
            val x = username.text.toString() // Username
            val y = password.text.toString() // Password
            val z = konfirmasipswd.text.toString() // Konfirmasi Password

            // Cek apakah username sama dengan password
            if (x.isEmpty() && y.isEmpty() && z.isEmpty()) {
                Toast.makeText(this, "Masukkan username dan password!", Toast.LENGTH_LONG).show()
            } else if (x.isEmpty()) {
                Toast.makeText(this, "Masukkan username kamu!", Toast.LENGTH_LONG).show()
            } else if (y.isEmpty() || z.isEmpty()) {
                Toast.makeText(this, "Masukkan kedua password!", Toast.LENGTH_LONG).show()
            } else if (y != z) {
                Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_LONG).show()
            } else {
                //set username ke SharedPreferences
                val editor = sharPref.edit()
                editor.putString("username", x)
                editor.putString("password", y)
                editor.apply()

                // intent untuk berpindah ke login
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }

        }

        masuk.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}