package com.example.feelora

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textName: TextView = findViewById(R.id.name)
        val btnBack: Button = findViewById(R.id.btnBack)
        val btnSnakeBar: Button = findViewById(R.id.btnSnackBar)
        val btnSnakeBar2: Button = findViewById(R.id.btnSnackBar2)
        val btnAlert: Button = findViewById(R.id.btnAlert)

        // ambil sharedPref
        val sharPref = getSharedPreferences("UserPref", MODE_PRIVATE)
        val username = sharPref.getString("username", null)
        textName.text = "Hai " + username.toString()

        btnBack.setOnClickListener{
            finish()
        }
        btnSnakeBar.setOnClickListener{
            val view = this.findViewById<View>(android.R.id.content)
            val snackbar = Snackbar.make(view, "Halo! ini Snack Bar que", Snackbar.LENGTH_LONG)

            snackbar.setAction("Undo"){
                Toast.makeText(this, "Tombol Undo di-klik", Toast.LENGTH_SHORT).show()
            }
            snackbar.show()
        }

        btnSnakeBar2.setOnClickListener{
            showSnackBar("Hai, ini dari pemanggilan fun showSnackBar()")
        }

        btnAlert.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes"){dialogInterface, which ->
                    dialogInterface.dismiss()
                    showSnackBar("Okay!")
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun showSnackBar(message: String){
        val view = this.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

        snackbar.setAction("Undo"){
            Toast.makeText(this, "Tombol Undo di-klik", Toast.LENGTH_SHORT).show()
        }
        snackbar.show()
    }
}