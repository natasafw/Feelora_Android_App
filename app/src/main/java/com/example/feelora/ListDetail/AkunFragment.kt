package com.example.feelora.ListDetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.feelora.R
import com.example.feelora.databinding.FragmentAkunBinding
import com.example.feelora.databinding.FragmentHomeBinding

class AkunFragment : Fragment() {
    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_akun, container, false)
        // Set the toolbar title for this fragment
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Akun"
        // Mengambil nama pengguna dari SharedPreferences
        val sharPref = requireContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val username = sharPref.getString("username", "User")
        binding.username.text = "Hai $username !"
        return binding.root
    }
}
