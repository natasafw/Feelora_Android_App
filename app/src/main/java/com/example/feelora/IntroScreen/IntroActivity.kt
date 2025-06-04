package com.example.feelora.IntroScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.feelora.LoginActivity
import com.example.feelora.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class IntroActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewPager = findViewById(R.id.viewPager)
        dotsIndicator = findViewById(R.id.dots_indicator)
        val btnNext: Button = findViewById(R.id.btnNext)
        val skip: TextView = findViewById(R.id.skip)

        val fragmentList = listOf(Intro1Fragment(), Intro2Fragment(), Intro3Fragment())
        val adapter = PageAdapter(this, fragmentList)

        viewPager.adapter = adapter

        btnNext.setOnClickListener {
            if(viewPager.currentItem < fragmentList.size - 1){
                viewPager.currentItem += 1
            } else {
                finishWelcome()
            }
        }

        skip.setOnClickListener{
            finishWelcome()
        }

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == fragmentList.size - 1){
                    btnNext.text = "Finish"
                    skip.visibility = View.GONE
                }
            }
        })

        dotsIndicator.attachTo(viewPager)
    }

    private fun finishWelcome(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}