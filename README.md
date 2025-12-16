# TrailSnap

TrailSnap is a simple Android application designed as a take-home coding exercise for Mobile/SDK Engineer candidates. The app displays a list of hiking trails with search functionality and includes a lightweight telemetry client that sends events to Honeycomb.

## Purpose

This repository serves as an interview exercise to assess:
- Android development skills (Kotlin, MVVM architecture)
- Integration of instrumentation/observability tooling
- Accessibility awareness
- Code quality and engineering practices

## What's Included

- **Android App**: A simple trails browsing app with search functionality
- **Telemetry Client**: A basic event tracking system that sends data to Honeycomb
- **Exercise Challenges**: See [EXERCISE.md](EXERCISE.md) for the coding tasks

## Prerequisites

Before you begin, ensure you have:

- **Android Studio** (Arctic Fox or later recommended)
- **Java Development Kit (JDK)** 11 or higher
- **Android SDK** with minimum API level 24
- A **Honeycomb account** (free trial available at [honeycomb.io](https://www.honeycomb.io))
- An Android **emulator** or physical device for testing

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd TrailSnap
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an Existing Project"
3. Navigate to the cloned `TrailSnap` directory
4. Click "OK" and wait for Gradle sync to complete

### 3. Configure Honeycomb Credentials

To enable telemetry tracking, you'll need to configure your Honeycomb API credentials:

1. **Sign up for Honeycomb** (if you haven't already):
   - Visit [honeycomb.io](https://www.honeycomb.io)
   - Create a free account

2. **Get your API key**:
   - Log in to your Honeycomb account
   - Navigate to "Account" → "Team Settings" → "API Keys"
   - Create a new API key or copy an existing one

3. **Create or identify a dataset**:
   - Datasets in Honeycomb are like databases for your events
   - You can create a new dataset called `trailsnap` or use an existing one
   - Note: The dataset will be created automatically when the first event arrives if it doesn't exist

4. **Update TelemetryConfig.kt**:
   - Open [app/src/main/java/com/honeycomb/trailsnap/telemetry/TelemetryConfig.kt](app/src/main/java/com/honeycomb/trailsnap/telemetry/TelemetryConfig.kt)
   - Replace the placeholder values:
     ```kotlin
     object TelemetryConfig {
         const val apiKey: String = "your-actual-api-key-here"
         const val datasetName: String = "trailsnap"  // or your chosen dataset name
         const val apiHost: String = "https://api.honeycomb.io"
     }
     ```

### 4. Build and Run the App

1. In Android Studio, select your target device (emulator or physical device)
2. Click the "Run" button (green play icon) or use the shortcut:
   - **Mac**: `Ctrl + R`
   - **Windows/Linux**: `Shift + F10`
3. Wait for the build to complete and the app to launch

Alternatively, you can build from the command line:

```bash
./gradlew assembleDebug
```

### 5. Verify Telemetry is Working

After launching the app, verify that the telemetry client is sending events:

1. **Check Android Studio Logcat**:
   - Look for log messages from `TelemetryClient`
   - You should see confirmation that initialization succeeded
   - If you see warnings about placeholder API keys, double-check step 3

2. **Verify in Honeycomb**:
   - Log in to your Honeycomb account
   - Navigate to your dataset (e.g., `trailsnap`)
   - Look for a `startup` event with:
     - `event_type: "startup"`
     - `platform: "android"`
   - This event is sent automatically when the app launches

3. **Troubleshooting**:
   - If you don't see events in Honeycomb:
     - Verify your API key and dataset name are correct
     - Check that your device/emulator has internet connectivity
     - Review Logcat for any error messages
     - Ensure the API key has write permissions for the dataset

## Project Structure

```
TrailSnap/
├── app/src/main/java/com/honeycomb/trailsnap/
│   ├── MainActivity.kt              # Main app activity
│   ├── model/
│   │   └── Trail.kt                 # Trail data model
│   ├── repository/
│   │   └── TrailsRepository.kt      # Data layer
│   ├── viewmodel/
│   │   └── TrailListViewModel.kt    # MVVM view model
│   ├── adapter/
│   │   └── TrailAdapter.kt          # RecyclerView adapter
│   └── telemetry/
│       ├── TelemetryConfig.kt       # Configuration for Honeycomb
│       └── TelemetryClient.kt       # Event tracking client
└── app/src/main/res/layout/
    └── activity_main.xml            # Main UI layout
```

## Architecture Overview

The app follows standard Android development patterns:

## Technology Stack

- **Language**: Kotlin
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35 (Android 15)
- **Key Libraries**:
  - AndroidX (AppCompat, Core KTX, ConstraintLayout)
  - Lifecycle components (ViewModel, LiveData)
  - Material Design Components
  - OkHttp (for HTTP requests)

## Next Steps

Once you have the app running and verified that telemetry is working:

1. **Read [EXERCISE.md](EXERCISE.md)** for the coding challenges
2. Complete the three challenges at your own pace (suggested timebox: 90 minutes)
3. Don't worry if you can't finish everything—we're more interested in your approach than completion

## Support

If you encounter any issues with setup or have questions about the exercise:

- Check the [EXERCISE.md](EXERCISE.md) file for additional guidance
- Review Logcat output for debugging information
- Ensure all prerequisites are properly installed
- Contact your interviewer if you're blocked on setup.

## License

This project is provided as an interview exercise. All rights reserved.
