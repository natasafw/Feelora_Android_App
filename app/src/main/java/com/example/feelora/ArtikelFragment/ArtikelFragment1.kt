package com.example.feelora.ArtikelFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.feelora.R

class ArtikelFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel1, container, false)
        val btnfg2: Button = view.findViewById(R.id.btnfg2)

        btnfg2.setOnClickListener {
            (activity as? ArtikelActivity)?.replaceFragment(ArtikelFragment2())
        }

        return view
    }
}