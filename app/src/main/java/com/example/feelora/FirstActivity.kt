package com.example.feelora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.feelora.ArtikelFragment.ArtikelActivity
import com.example.feelora.ListView.ListViewActivity
import com.example.feelora.RecyclerView.RecyclerViewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FirstActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fisrt)

        // Mengatur Edge to Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengambil nama pengguna dari SharedPreferences
        val sharPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val username = sharPref.getString("username", "User")
        findViewById<TextView>(R.id.username).text = "Hai $username !"

        // Mengambil intent untuk artikel fragment
        val gambar1: ImageView = findViewById(R.id.gambar1)
        val gambar2: ImageView = findViewById(R.id.gambar2)
        val gambar3: ImageView = findViewById(R.id.gambar3)

        // Set onClickListener untuk setiap gambar
        gambar1.setOnClickListener {
            val intent = Intent(this, ArtikelActivity::class.java)
            intent.putExtra("FRAGMENT_TYPE", 1) // Fragment untuk gambar 1
            startActivity(intent)
        }

        gambar2.setOnClickListener {
            val intent = Intent(this, ArtikelActivity::class.java)
            intent.putExtra("FRAGMENT_TYPE", 2) // Fragment untuk gambar 2
            startActivity(intent)
        }

        gambar3.setOnClickListener {
            val intent = Intent(this, ArtikelActivity::class.java)
            intent.putExtra("FRAGMENT_TYPE", 3) // Fragment untuk gambar 3
            startActivity(intent)
        }

        // Atur BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    //startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_calendar -> {
                    //startActivity(Intent(this, CalendarActivity::class.java))
                    true
                }
                R.id.nav_journal -> {
                    startActivity(Intent(this, RecyclerViewActivity::class.java))
                    true
                }
                R.id.nav_setting -> {
                    startActivity(Intent(this, ListViewActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Profil Popup Menu
        val profileImage = findViewById<ImageView>(R.id.profile_image)
        profileImage.setOnClickListener {
            showProfilePopupMenu(profileImage)
        }

        // Event untuk tombol
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.btnWebView).setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
    }

    // Fungsi untuk menampilkan Popup Menu profil
    private fun showProfilePopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.logout -> {
                    val sharPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
                    val editor = sharPref.edit()
                    editor.clear().apply()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }
}
