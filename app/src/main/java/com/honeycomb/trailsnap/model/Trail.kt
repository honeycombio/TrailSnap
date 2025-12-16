package com.honeycomb.trailsnap.model

/**
 * Data model representing a hiking trail.
 *
 * @property id Unique identifier for the trail
 * @property name Display name of the trail
 * @property difficulty Difficulty level (e.g., "Easy", "Moderate", "Hard")
 */
data class Trail(
    val id: Int,
    val name: String,
    val difficulty: String
)
