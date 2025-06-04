package com.example.gulaku.main.jurnal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feelora.BasicApi.data.model.JournalRequest
import com.example.feelora.BasicApi.data.network.RetrofitInstance
import com.example.feelora.BasicApi.data.repository.JournalRepository
import com.example.feelora.BasicApi.utils.Resource
import com.example.feelora.BasicApi.utils.ViewModelFactory
import com.example.feelora.databinding.FragmentJurnalBinding
import com.example.feelora.main.home.JournalAdapter
import com.example.gulaku.basic_api.ui.viewmodel.JournalViewModel
import com.google.android.material.snackbar.Snackbar

class JurnalFragment : Fragment() {
    private var _binding: FragmentJurnalBinding? = null
    private val binding get() = _binding!!

    private val journalViewModel: JournalViewModel by activityViewModels {
        ViewModelFactory(JournalViewModel::class.java) {
            val repository = JournalRepository(
                RetrofitInstance.getCrudApi()
            )
            JournalViewModel(repository)
        }
    }

    private lateinit var adapter: JournalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJurnalBinding.inflate(inflater, container, false)

        adapter = JournalAdapter(emptyList()) { uuid ->
            deleteJournalEntry(uuid) // Panggil fungsi hapus dengan UUID
        }
        binding.jurnalView.adapter = adapter
        binding.jurnalView.layoutManager = LinearLayoutManager(this.context)

        getJournalEntries()
        //createJournalEntry()

        return binding.root
    }

    private fun getJournalEntries() {
        journalViewModel.getJournalEntries(requireContext())
        journalViewModel.data.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Loading Data", "Mohon Tunggu...")
                    binding.loadingJurnal.root.visibility = View.VISIBLE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.GONE
                    binding.jurnalView.visibility = View.GONE
                }

                is Resource.Success -> {
                    Log.d("Success", "Data berhasil diambil dari server")
                    binding.loadingJurnal.root.visibility = View.GONE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.GONE
                    binding.jurnalView.visibility = View.VISIBLE

                    // Update adapter dengan data yang diambil
                    val journalEntries = resource.data!!.items // Mengambil list JournalItems
                    adapter.updateData(journalEntries) // Pastikan ini menggunakan JournalItems
                }

                is Resource.Empty -> {
                    Log.d("Empty Data", "Data Tidak Ditemukan")
                    binding.loadingJurnal.root.visibility = View.GONE
                    binding.emptyJurnal.root.visibility = View.VISIBLE
                    binding.errorJurnal.root.visibility = View.GONE
                    binding.jurnalView.visibility = View.GONE
                }

                is Resource.Error -> {
                    Log.d("Request Error", "Terjadi Kesalahan: ${resource.message}")
                    binding.loadingJurnal.root.visibility = View.GONE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.VISIBLE
                    binding.jurnalView.visibility = View.GONE
                    binding.errorJurnal.root.setOnClickListener {
                        journalViewModel.getJournalEntries(requireContext(), true)
                    }
                }
            }
        }
    }

    private fun createJournalEntry() {
        val name = "Reflections on My Goals"
        val journal =
            "Today, I reflected on my goals for the upcoming year. I feel motivated to make positive changes in my life."
        val date = "09-12-2024"
        val image =
            "https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"

        val journalEntry = JournalRequest(date, name, journal, image)

        journalViewModel.createJournalEntry(requireContext(), journalEntry)
        journalViewModel.createStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Loading Data", "Mengirim Data...")
                    binding.loadingJurnal.root.visibility = View.VISIBLE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.GONE
                    binding.jurnalView.visibility = View.GONE
                }

                is Resource.Success -> {
                    Log.d("Success", "Data berhasil dibuat")
                    binding.loadingJurnal.root.visibility = View.GONE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.GONE
                    binding.jurnalView.visibility = View.VISIBLE

                    Snackbar.make(
                        binding.root,
                        "Journal entry created successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Log.d("Request Error", "Terjadi Kesalahan: ${resource.message}")
                    binding.loadingJurnal.root.visibility = View.GONE
                    binding.emptyJurnal.root.visibility = View.GONE
                    binding.errorJurnal.root.visibility = View.VISIBLE
                    binding.jurnalView.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        resource.message ?: "Failed to create journal entry.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    private fun deleteJournalEntry(uuid: String) {
        journalViewModel.deleteJournalEntry(
            requireContext(),
            uuid
        ) // Menggunakan UUID untuk menghapus
        journalViewModel.deleteStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Log.d("Loading Data", "Menghapus Data...")
                }

                is Resource.Success -> {
                    Log.d("Success", "Data berhasil dihapus")
                    Snackbar.make(
                        binding.root,
                        "Journal entry deleted successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    journalViewModel.getJournalEntries(requireContext())
                    journalViewModel.data.observe(viewLifecycleOwner) { resource ->
                        when (resource) {
                            is Resource.Loading -> {
                                Log.d("Loading Data", "Mohon Tunggu...")
                                binding.loadingJurnal.root.visibility = View.VISIBLE
                                binding.emptyJurnal.root.visibility = View.GONE
                                binding.errorJurnal.root.visibility = View.GONE
                                binding.jurnalView.visibility = View.GONE
                            }

                            is Resource.Success -> {
                                Log.d("Success", "Data berhasil diambil dari server")
                                binding.loadingJurnal.root.visibility = View.GONE
                                binding.emptyJurnal.root.visibility = View.GONE
                                binding.errorJurnal.root.visibility = View.GONE
                                binding.jurnalView.visibility = View.VISIBLE

                                // Update adapter dengan data yang diambil
                                val journalEntries = resource.data!!.items // Mengambil list JournalItems
                                adapter.updateData(journalEntries) // Pastikan ini menggunakan JournalItems
                            }

                            is Resource.Empty -> {
                                Log.d("Empty Data", "Data Tidak Ditemukan")
                                binding.loadingJurnal.root.visibility = View.GONE
                                binding.emptyJurnal.root.visibility = View.VISIBLE
                                binding.errorJurnal.root.visibility = View.GONE
                                binding.jurnalView.visibility = View.GONE
                            }

                            is Resource.Error -> {
                                Log.d("Request Error", "Terjadi Kesalahan: ${resource.message}")
                                binding.loadingJurnal.root.visibility = View.GONE
                                binding.emptyJurnal.root.visibility = View.GONE
                                binding.errorJurnal.root.visibility = View.VISIBLE
                                binding.jurnalView.visibility = View.GONE
                                binding.errorJurnal.root.setOnClickListener {
                                    journalViewModel.getJournalEntries(requireContext(), true)
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Log.d("Request Error", "Terjadi Kesalahan: ${resource.message}")
                    Snackbar.make(
                        binding.root,
                        resource.message ?: "Failed to delete journal entry.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
