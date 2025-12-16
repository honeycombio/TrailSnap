package com.honeycomb.trailsnap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.honeycomb.trailsnap.model.Trail
import com.honeycomb.trailsnap.repository.TrailsRepository

/**
 * ViewModel for managing the trails list and search functionality.
 */
class TrailListViewModel : ViewModel() {

    private val repository = TrailsRepository()

    private val _trails = MutableLiveData<List<Trail>>()
    val trails: LiveData<List<Trail>> = _trails

    init {
        // Load all trails initially
        loadAllTrails()
    }

    /**
     * Loads all available trails.
     */
    private fun loadAllTrails() {
        _trails.value = repository.getAllTrails()
    }

    /**
     * Performs a search for trails based on the provided query and optional filter.
     *
     * @param query Search term to match against trail names
     * @param filter Optional difficulty filter (e.g., "Easy", "Moderate", "Hard")
     */
    fun search(query: String, filter: String? = null) {
        _trails.value = repository.searchTrails(query, filter)
    }
}
