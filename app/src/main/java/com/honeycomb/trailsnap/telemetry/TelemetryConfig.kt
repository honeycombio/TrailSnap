package com.honeycomb.trailsnap.telemetry

/**
 * Configuration object for telemetry integration with Honeycomb.
 *
 * Candidates should replace the placeholder values with their actual Honeycomb credentials:
 * - apiKey: Your Honeycomb API key (from https://ui.honeycomb.io/account)
 * - datasetName: The name of the dataset to send events to
 */
object TelemetryConfig {
    const val apiKey: String = "YOUR_API_KEY_HERE"
    const val datasetName: String = "YOUR_DATASET_NAME_HERE"
    const val apiHost: String = "https://api.honeycomb.io"
}
