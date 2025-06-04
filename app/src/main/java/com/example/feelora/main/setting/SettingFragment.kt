package com.example.feelora.main.setting

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.feelora.ListDetail.ListDetailActivity
import com.example.feelora.ListView.ListAdapter
import com.example.feelora.ListView.ListModel
import com.example.feelora.R
import com.example.feelora.WebViewActivity
import com.example.feelora.databinding.FragmentHomeBinding
import com.example.feelora.databinding.FragmentSettingBinding
import com.example.feelora.main.home.HomeNewsHorizontalAdapter

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_setting, container, false)
//        val setting: ListView = view.findViewById(R.id.list_item)

        val menuList = listOf(
            ListModel(1, "Akun", "Akun 1", R.drawable.ic_person),
            ListModel(2, "Notifikasi", "Aktif", R.drawable.ic_notifikasi),
            ListModel(3, "Bahasa", "Bahasa Indonesia", R.drawable.ic_language),
            ListModel(4, "Tampilan", "Default", R.drawable.ic_apperance),
            ListModel(5, "Tentang Aplikasi", "feelora v.1.20.i", R.drawable.ic_app),
        )

        binding.listItem.adapter = this.context?.let { ListAdapter(it, menuList) }

        binding.listItem.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = menuList[position]
            // Create an Intent using the Fragment's context
            val intent = Intent(requireContext(), ListDetailActivity::class.java)
            // Pass only the ID to ListDetailActivity
            intent.putExtra("item_id", selectedItem.id)
            // Use startActivity() from the Fragment context
            startActivity(intent)
        }

        return binding.root
    }

}