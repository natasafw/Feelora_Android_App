package com.example.feelora.ListDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.feelora.R
import com.example.feelora.databinding.FragmentHomeBinding
import com.example.feelora.databinding.FragmentNotifikasiBinding

class NotifikasiFragment : Fragment() {
    private var _binding: FragmentNotifikasiBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_notifikasi, container, false)
        // Set the toolbar title for this fragment
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Notifikasi"
        return binding.root
    }
}