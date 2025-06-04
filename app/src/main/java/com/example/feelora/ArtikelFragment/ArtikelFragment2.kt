package com.example.feelora.ArtikelFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.feelora.R

class ArtikelFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel2, container, false)
        val btnfg1: Button = view.findViewById(R.id.btnfg1)
        val btnfg3: Button = view.findViewById(R.id.btnfg3)

        btnfg1.setOnClickListener {
            (activity as? ArtikelActivity)?.replaceFragment(ArtikelFragment1())
        }

        btnfg3.setOnClickListener {
            val thirdFragment = ArtikelFragment3()
            val bundle = Bundle()
            bundle.putString("Link", "https://www.ourbetterworld.org")
            thirdFragment.arguments = bundle
            (activity as? ArtikelActivity)?.replaceFragment(thirdFragment)
        }
        return view
    }

}