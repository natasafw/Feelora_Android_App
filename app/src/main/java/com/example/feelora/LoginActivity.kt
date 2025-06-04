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
import com.example.feelora.main.MainActivity

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val btnmasuk: Button = findViewById(R.id.btnmasuk)
        val daftar: TextView = findViewById(R.id.daftar)
        val sharPref: SharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE)

        val isLogin = sharPref.getString("isLogin", null)
        if(isLogin == "1"){
            goToMain()
            finish()
        }

        btnmasuk.setOnClickListener{
            val x = username.text.toString()
            val y = password.text.toString()
            val user = sharPref.getString("username", null)
            val pass = sharPref.getString("password", null)
            if (x.isEmpty() || y.isEmpty()) {
                Toast.makeText(this, "Masukkan username dan password!", Toast.LENGTH_LONG).show()
            } else if (x == user && y == pass) {
                val editor = sharPref.edit()
                editor.putString("isLogin", "1")
                editor.apply()

                // Intent untuk berpindah ke MainActivity
                goToMain()
                finish()
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_LONG).show()
            }

        }

        daftar.setOnClickListener{
            val i = Intent(this, DaftarActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}