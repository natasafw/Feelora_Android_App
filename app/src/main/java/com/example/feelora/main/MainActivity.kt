package com.example.feelora.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.feelora.LoginActivity
import com.example.feelora.R
import com.example.feelora.main.calendar.CalendarFragment
import com.example.feelora.main.home.HomeFragment
import com.example.feelora.main.setting.SettingFragment
import com.example.gulaku.main.jurnal.JurnalFragment

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Mengambil nama pengguna dari SharedPreferences
        val sharPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val username = sharPref.getString("username", "User")
        findViewById<TextView>(R.id.username).text = "Hai $username !"

        // Profil Popup Menu
        val profileImage = findViewById<ImageView>(R.id.profile_image)
        profileImage.setOnClickListener {
            showProfilePopupMenu(profileImage)
        }

        loadFragment(HomeFragment())

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_calendar -> {
                    loadFragment(CalendarFragment())
                }
                R.id.nav_journal -> {
                    loadFragment(JurnalFragment())
                }
                R.id.nav_setting -> {
                    loadFragment(SettingFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
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

}