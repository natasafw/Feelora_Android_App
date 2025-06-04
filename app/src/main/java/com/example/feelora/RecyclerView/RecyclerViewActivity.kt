package com.example.feelora.RecyclerView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feelora.R

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var toolbar: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Atur judul di toolbar
        supportActionBar?.title = "Journal"

        // Inisialisasi tombol back di toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerView.layoutManager = GridLayoutManager(this, 2)

        val items = listOf(
            RecyclerModel("https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 1"),
            RecyclerModel("https://img.freepik.com/free-photo/face-expressions-illustrations-emotions-feelings_53876-128071.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 2"),
            RecyclerModel("https://img.freepik.com/free-vector/woman-expressing-various-feelings-emotions-cartoon-female-character-suffering-from-distracted-behavior-mood-changes-flat-vector-illustration-psychological-mental-health-concept_74855-22598.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 3"),
            RecyclerModel("https://img.freepik.com/free-vector/hand-drawn-emotes-element-collection_23-2149961832.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 4"),
            RecyclerModel("https://img.freepik.com/free-vector/woman-expressing-strong-various-feelings-emotions_74855-5593.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 5"),
            RecyclerModel("https://img.freepik.com/free-photo/face-expressions-illustrations-emotions-feelings_53876-123916.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 6"),
            RecyclerModel("https://img.freepik.com/free-photo/flat-lay-assortment-with-different-feelings_23-2148860328.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 7"),
            RecyclerModel("https://img.freepik.com/free-photo/face-expressions-illustrations-emotions-feelings_53876-128079.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 8"),
            RecyclerModel("https://img.freepik.com/free-photo/people-faces-covered-with-happy-expression-emotion-balloons_53876-146403.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 9"),
            RecyclerModel("https://img.freepik.com/free-photo/front-view-arrangement-with-different-feelings_23-2148860247.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 10"),
            RecyclerModel("https://img.freepik.com/free-photo/front-view-arrangement-with-different-feelings_23-2148860246.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 11"),
            RecyclerModel("https://img.freepik.com/free-photo/flat-lay-arrangement-with-different-feelings_23-2148860315.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 12"),
            RecyclerModel("https://img.freepik.com/free-photo/flat-lay-assortment-with-different-feelings_23-2148860324.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 13"),
            RecyclerModel("https://img.freepik.com/free-photo/front-view-arrangement-with-different-feelings_23-2148860244.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 14"),
            RecyclerModel("https://img.freepik.com/free-vector/flat-design-schizophrenia-illustration_23-2149364625.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid", "Description 15"),
            RecyclerModel("https://img.freepik.com/free-photo/top-view-arrangement-with-different-feelings_23-2148860308.jpg?t=st=1730114205~exp=1730117805~hmac=a5bd7b9b833211a7729ee6b2cea48a6509a9a18faa04a703d12520253d606187&w=740", "Description 16"),
            RecyclerModel("https://img.freepik.com/free-vector/abused-crying-woman-pro-civil-rights-concept_23-2148583080.jpg?t=st=1730114205~exp=1730117805~hmac=d4fca8aafca94fafdbaceb0a8df6804152bda46ea4481ae040802a73cc42413d&w=740", "Description 17"),
            RecyclerModel("https://img.freepik.com/free-vector/illustration-world-mental-health-day-awareness_52683-134651.jpg?t=st=1730114205~exp=1730117805~hmac=bdcc8dac640031521a1326d7f0cdb4ad54966606d77f0575950f21d32b194541&w=740", "Description 18"),
            RecyclerModel("https://img.freepik.com/free-vector/flat-illustration-world-mental-health-day-awareness_23-2150743454.jpg?t=st=1730114206~exp=1730117806~hmac=24df2e2dfe94cb52fdbf4cc9c3e17c6d5616fff032f2aa46a67b217dfc9f1a6c&w=740", "Description 19"),
            RecyclerModel("https://img.freepik.com/free-vector/three-girls-gestures-characters-group_603843-426.jpg?t=st=1730114206~exp=1730117806~hmac=0236396f521f998c763ace89dfe5713cc1d431aea3c9e818387dc97500a8f0e4&w=900", "Description 20"),
            RecyclerModel("https://img.freepik.com/free-vector/female-hands-making-heart_603843-3404.jpg?t=st=1730114206~exp=1730117806~hmac=9c768e36f17206922b72402f1f8d7034b3752c26a3793037b9ed5f5d86fe279c&w=740", "Description 21"),
            RecyclerModel("https://img.freepik.com/free-vector/flat-design-affectionate-lesbian-kiss_23-2149014251.jpg?t=st=1730114206~exp=1730117806~hmac=2ce1c7043a9663323e22b9dd8b576a08e2f68154ebb320320c14923b343a5feb&w=740", "Description 22"),
            RecyclerModel("https://img.freepik.com/free-vector/hand-drawn-flat-design-shrug-illustration_23-2149349341.jpg?t=st=1730114207~exp=1730117807~hmac=3fafcfe62e608c32c23363060cdd62880ac0c20e4f0f0093fac8ce72a5ff6cd8&w=740", "Description 23"),
            RecyclerModel("https://img.freepik.com/free-vector/gender-identity-concept_23-2148552767.jpg?t=st=1730114207~exp=1730117807~hmac=a9a5203b61a621061f850c02c19709330bcf887888711b443014b540d80f7c84&w=740", "Description 24"),
            RecyclerModel("https://img.freepik.com/free-vector/illustration-public-approval-concept_23-2148403845.jpg?t=st=1730114207~exp=1730117807~hmac=638eb94882884e0b0a0188f69e9d4a2f7fcb19ee690f51f8a84d65a0bdeb2721&w=740", "Description 25"),
            RecyclerModel("https://img.freepik.com/free-vector/crying-woman-being-scared-by-spooky-pumpkin_23-2149078502.jpg?t=st=1730114602~exp=1730118202~hmac=ff1b8ebb786549715d0a4998067e6e8377063e1ef4de99d352d15f98e04c2096&w=740", "Description 26"),
            RecyclerModel("https://img.freepik.com/free-vector/illustration-people-with-mental-health-problems_52683-69075.jpg?t=st=1730114618~exp=1730118218~hmac=ce69a41ee896f5dcfef25567ff2841354eb429c576080fdd0f311703052eea49&w=740", "Description 27"),
            RecyclerModel("https://img.freepik.com/free-vector/flat-world-mental-health-day-illustration_23-2149635929.jpg?t=st=1730114635~exp=1730118235~hmac=07df68b303e92425f95d5e951b31a7d3e52697a8a10f8049e44226f5a6860206&w=740", "Description 28"),
            RecyclerModel("https://img.freepik.com/free-vector/freedom-concept-illustration_114360-15728.jpg?t=st=1730114653~exp=1730118253~hmac=4cdfe9b7a8958282d6802063e2e6a912218a03fa5b25fad72b5582328ff6c44e&w=740", "Description 29"),
            RecyclerModel("https://img.freepik.com/free-vector/bipolar-disorder-concept-illustration_114360-3646.jpg?t=st=1730114671~exp=1730118271~hmac=93b6ada665e93ec75dc77cc53b08f6ffe987883c005a866b87afe6fabb48c34b&w=740", "Description 30")
        )

        adapter = RecyclerAdapter(items)
        recyclerView.adapter = adapter
    }
    // Fungsi untuk menangani tombol back pada toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Tutup activity ketika tombol back ditekan
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}