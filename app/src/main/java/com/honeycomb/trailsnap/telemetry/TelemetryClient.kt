package com.honeycomb.trailsnap.telemetry

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Simple telemetry client for sending events to Honeycomb.
 *
 * This client provides basic instrumentation capabilities:
 * - Initialization with automatic startup event
 * - Event tracking with custom attributes
 * - Graceful handling of missing configuration
 *
 * Usage:
 * ```
 * // Initialize once at app startup
 * TelemetryClient.init()
 *
 * // Track events throughout the app
 * TelemetryClient.trackEvent(
 *     name = "search",
 *     attributes = mapOf("query" to searchTerm)
 * )
 * ```
 */
object TelemetryClient {
    private const val TAG = "TelemetryClient"
    private var initialized = false
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Initialize the telemetry client.
     *
     * This should be called once at application startup (e.g., in MainActivity.onCreate).
     * If API credentials are not configured, initialization will fail gracefully with a warning,
     * and subsequent trackEvent calls will be no-ops.
     *
     * On successful initialization, sends a "startup" event with platform information.
     */
    fun init() {
        // Check if configuration is still using placeholder values
        if (TelemetryConfig.apiKey == "YOUR_API_KEY_HERE" ||
            TelemetryConfig.datasetName == "YOUR_DATASET_NAME_HERE"
        ) {
            Log.w(
                TAG,
                "TelemetryClient not initialized: API key or dataset name not configured. " +
                        "Please update TelemetryConfig with your Honeycomb credentials."
            )
            initialized = false
            return
        }

        initialized = true
        Log.i(TAG, "TelemetryClient initialized successfully")

        // Send startup event
        trackEvent(
            name = "startup",
            attributes = mapOf("platform" to "android")
        )
    }

    /**
     * Track a custom event with optional attributes.
     *
     * Events are sent asynchronously to Honeycomb and will not block the calling thread.
     * If the client is not initialized, the event will be logged but not sent.
     *
     * @param name The event name (will be sent as "event_type" field)
     * @param attributes Additional key-value pairs to include in the event
     */
    fun trackEvent(
        name: String,
        attributes: Map<String, Any?> = emptyMap()
    ) {
        if (!initialized) {
            Log.d(TAG, "Skipping event '$name' - client not initialized")
            return
        }

        try {
            // Build JSON payload
            val json = JSONObject().apply {
                put("event_type", name)
                // Add all attributes as top-level fields
                attributes.forEach { (key, value) ->
                    put(key, value)
                }
            }

            // Build HTTP request
            val url = "${TelemetryConfig.apiHost}/1/events/${TelemetryConfig.datasetName}"
            val requestBody = json.toString()
                .toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url(url)
                .header("X-Honeycomb-Team", TelemetryConfig.apiKey)
                .post(requestBody)
                .build()

            // Send asynchronously
            httpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "Failed to send event '$name': ${e.message}", e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (response.isSuccessful) {
                            Log.d(TAG, "Event '$name' sent successfully")
                        } else {
                            Log.w(
                                TAG,
                                "Failed to send event '$name': HTTP ${response.code} ${response.message}"
                            )
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "Error building event '$name': ${e.message}", e)
        }
    }
}
