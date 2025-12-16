package com.honeycomb.trailsnap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.trailsnap.adapter.TrailAdapter
import com.honeycomb.trailsnap.telemetry.TelemetryClient
import com.honeycomb.trailsnap.viewmodel.TrailListViewModel

/**
 * Main activity for the TrailSnap app.
 * Displays a list of hiking trails with search functionality.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TrailListViewModel
    private lateinit var adapter: TrailAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize telemetry
        TelemetryClient.init()

        // Initialize views
        searchEditText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        recyclerView = findViewById(R.id.trails_recycler_view)

        // Set up RecyclerView
        adapter = TrailAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Set up ViewModel
        viewModel = ViewModelProvider(this)[TrailListViewModel::class.java]

        // Observe trails list
        viewModel.trails.observe(this) { trails ->
            adapter.submitList(trails)
        }

        // Set up search button click listener
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            viewModel.search(query, filter = null)
        }
    }
}
