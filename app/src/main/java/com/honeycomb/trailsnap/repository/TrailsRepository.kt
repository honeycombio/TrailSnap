package com.honeycomb.trailsnap.repository

import com.honeycomb.trailsnap.model.Trail

/**
 * Repository for managing trail data.
 * Currently uses an in-memory list for simplicity.
 */
class TrailsRepository {

    private val allTrails = listOf(
        Trail(1, "Eagle Peak Trail", "Hard"),
        Trail(2, "Meadow Loop", "Easy"),
        Trail(3, "River Bend Path", "Moderate"),
        Trail(4, "Summit Ridge Trail", "Hard"),
        Trail(5, "Pine Forest Walk", "Easy"),
        Trail(6, "Canyon View Trail", "Moderate"),
        Trail(7, "Lakeside Path", "Easy"),
        Trail(8, "Mountain Pass Trail", "Hard"),
        Trail(9, "Woodland Loop", "Easy"),
        Trail(10, "Vista Point Trail", "Moderate"),
        Trail(11, "Rocky Ridge Trail", "Hard"),
        Trail(12, "Sunset Valley Path", "Moderate"),
        Trail(13, "Creekside Walk", "Easy"),
        Trail(14, "Alpine Summit Trail", "Hard"),
        Trail(15, "Wildflower Meadow Loop", "Easy")
    )

    /**
     * Returns all available trails.
     */
    fun getAllTrails(): List<Trail> {
        return allTrails
    }

    /**
     * Searches trails by name and optionally filters by difficulty.
     *
     * @param query Search term to match against trail names (case-insensitive)
     * @param filter Optional difficulty filter (e.g., "Easy", "Moderate", "Hard")
     * @return List of trails matching the search criteria
     */
    fun searchTrails(query: String, filter: String? = null): List<Trail> {
        var results = if (query.isBlank()) {
            allTrails
        } else {
            allTrails.filter { trail ->
                trail.name.contains(query, ignoreCase = true)
            }
        }

        // Apply difficulty filter if provided
        filter?.let { difficultyFilter ->
            if (difficultyFilter.isNotBlank()) {
                results = results.filter { trail ->
                    trail.difficulty.equals(difficultyFilter, ignoreCase = true)
                }
            }
        }

        return results
    }
}
