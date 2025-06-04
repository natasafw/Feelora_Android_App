package com.example.feelora

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.feelora.IntroScreen.IntroActivity
import java.util.logging.Handler
import kotlin.math.log

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Menunggu 3 detik sebelum berpindah ke LoginActivity
        android.os.Handler().postDelayed({
            val i = Intent(this, IntroActivity::class.java)
            startActivity(i)
            finish() // Menghapus splash screen dari stack agar tidak bisa kembali
        }, 1500) // 1500 milidetik = 1.5 detik
    }
}