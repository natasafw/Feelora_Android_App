package com.example.feelora.main.home

import AutoSliderAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feelora.BasicApi.data.network.RetrofitInstance
import com.example.feelora.BasicApi.data.repository.HeadlinesRepository
import com.example.feelora.BasicApi.ui.viewmodel.HeadlinesViewModel
import com.example.feelora.BasicApi.utils.Resource
import com.example.feelora.SecondActivity
import com.example.feelora.WebViewActivity
import com.example.feelora.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeNewsHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Event untuk tombol
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireActivity(), SecondActivity::class.java))
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireActivity(), WebViewActivity::class.java))
        }

        setupAutoSlide(binding)

        setupGridMenu(binding)

        //setupNewsHorizontal(binding)

        setupNewsApi(binding)

        return binding.root
    }

    private fun setupNewsApi(binding: FragmentHomeBinding) {
        // Inisialisasi ViewModel
        val repository = HeadlinesRepository(RetrofitInstance.apiService)
        val viewModel = HeadlinesViewModel(repository)

        // Setup adapter
        adapter = HomeNewsHorizontalAdapter(emptyList())
        binding.newsHorizontalList.adapter = adapter
        binding.newsHorizontalList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        // Observasi data
        viewModel.data.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Menampilkan loading
                    Log.d("GET Data News", "Loading...")
                    binding.loadingNewVertical.root.visibility = View.VISIBLE
                    binding.emptyNewVertical.root.visibility = View.GONE
                    binding.errorNewVertical.root.visibility = View.GONE
                    binding.newsHorizontalList.visibility = View.GONE
                }
                is Resource.Success -> {
                    // Menampilkan data
                    Log.d("GET Data News", "Data berhasil didapatkan!")
                    binding.loadingNewVertical.root.visibility = View.GONE
                    binding.emptyNewVertical.root.visibility = View.GONE
                    binding.errorNewVertical.root.visibility = View.GONE
                    binding.newsHorizontalList.visibility = View.VISIBLE

                    val articles = resource.data // Ambil daftar artikel dari Resource.Success
                    val items = articles?.map { article ->
                        HomeNewsHorizontalModel(
                            menuName = article.title,
                            imageUrl = article.urlToImage
                                ?: "https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
                        )
                    }
                    if (items != null) {
                        adapter.updateData(items)
                    }
                }
                is Resource.Empty -> {
                    // Menampilkan empty state
                    Log.d("GET Data News", "No articles found")
                    binding.loadingNewVertical.root.visibility = View.GONE
                    binding.emptyNewVertical.root.visibility = View.VISIBLE
                    binding.errorNewVertical.root.visibility = View.GONE
                    binding.newsHorizontalList.visibility = View.GONE
                }
                is Resource.Error -> {
                    // Menampilkan error state
                    Log.e("GET Data News", "Error: ${resource.message}")
                    binding.loadingNewVertical.root.visibility = View.GONE
                    binding.emptyNewVertical.root.visibility = View.GONE
                    binding.errorNewVertical.root.visibility = View.VISIBLE
                    binding.newsHorizontalList.visibility = View.GONE
                }
            }
        }

        // Memanggil fungsi untuk mengambil data
        viewModel.getHeadlines(requireContext())
    }

    private fun setupAutoSlide(binding: FragmentHomeBinding) {
        val images = listOf(
            "https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid",
            "https://img.freepik.com/free-vector/female-hands-making-heart_603843-3404.jpg?t=st=1730114206~exp=1730117806~hmac=9c768e36f17206922b72402f1f8d7034b3752c26a3793037b9ed5f5d86fe279c&w=740",
            "https://img.freepik.com/free-photo/face-expressions-illustrations-emotions-feelings_53876-123916.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid",
        )

        // Inisialisasi ViewPager2 dan WormDotsIndicator
        // val viewPager: ViewPager2 = view.findViewById(R.id.autoSlider)
        // val dotsIndicator: WormDotsIndicator = view.findViewById(R.id.dots_indicator)

        // Atur adapter dengan daftar URL gambar
        binding.autoSlider.adapter = AutoSliderAdapter(images, binding.autoSlider)
        binding.dotsIndicator.attachTo(binding.autoSlider)
    }

    private fun setupGridMenu(binding: FragmentHomeBinding) {
        binding.includeMenuHomeGrid.cardMenu1.setOnClickListener {
            Toast.makeText(context, "Card Menu 1 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeMenuHomeGrid.cardMenu2.setOnClickListener {
            Toast.makeText(context, "Card Menu 2 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeMenuHomeGrid.cardMenu3.setOnClickListener {
            Toast.makeText(context, "Card Menu 3 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeMenuHomeGrid.cardMenu4.setOnClickListener {
            Toast.makeText(context, "Card Menu 4 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeMenuHomeGrid.cardMenu5.setOnClickListener {
            Toast.makeText(context, "Card Menu 5 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeMenuHomeGrid.cardMenu6.setOnClickListener {
            Toast.makeText(context, "Card Menu 6 Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupNewsHorizontal(binding: FragmentHomeBinding) {
        val items = listOf(
            HomeNewsHorizontalModel(
                "Artikel 1",
                "https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
            ),
            HomeNewsHorizontalModel(
                "Artikel 2",
                "https://img.freepik.com/free-photo/face-expressions-illustrations-emotions-feelings_53876-128071.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
            ),
            HomeNewsHorizontalModel(
                "Artikel 3",
                "https://img.freepik.com/free-vector/woman-expressing-various-feelings-emotions-cartoon-female-character-suffering-from-distracted-behavior-mood-changes-flat-vector-illustration-psychological-mental-health-concept_74855-22598.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
            ),
            HomeNewsHorizontalModel(
                "Artikel 4",
                "https://img.freepik.com/free-vector/hand-drawn-emotes-element-collection_23-2149961832.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
            ),
        )

        binding.newsHorizontalList.adapter = HomeNewsHorizontalAdapter(items)
        binding.newsHorizontalList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }
}

