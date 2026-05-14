# Santhe-Connect

Android application built with **Kotlin + Jetpack Compose** for discovering authentic
local food, traditional santhe markets, homestays, and craft centers across Karnataka.

Developed during the **MindMatrix Internship (BEC803)** by **Akshata**.

## Modules
- **Home Dashboard** – category entry points (Food, Markets, Stays, Crafts)
- **Nearby Places** – search, filter, and open in Google Maps
- **Santhe Calendar** – upcoming weekly santhe events
- **Traveler's Wall** – community reviews & posts
- **Feedback** – submit ratings and feedback
- **Multilingual** – English + Kannada (`values-kn/`)

## Tech
Kotlin · Jetpack Compose · Material 3 · Navigation Compose · Retrofit · Google Maps intent

## Run
1. Open the project in **Android Studio Hedgehog or newer**.
2. Add your Google Maps API key in `AndroidManifest.xml` (`YOUR_GOOGLE_MAPS_API_KEY`).
3. Sync Gradle and run on an emulator or device (minSdk 24).

## Project structure
```
app/src/main/java/com/mindmatrix/santheconnect/
├── MainActivity.kt
├── data/            # Models + sample repository
├── navigation/      # NavGraph + bottom nav
└── ui/
    ├── screens/     # Home, Nearby, Calendar, Wall, Feedback
    └── theme/       # Material 3 theme
```
