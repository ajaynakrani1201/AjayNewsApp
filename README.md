** AjayNewsApp

AjayNewsApp is a modern Android news reader app built using Jetpack Compose, MVVM, Hilt, Retrofit, and Native C++ for API key protection. It fetches top news headlines related to India using the NewsAPI.

** Features

Clean MVVM architecture
API key stored securely using native C++ (.so file)
News fetched using Retrofit and displayed using Jetpack Compose
State management via ViewModel and Kotlin Flow
Navigation using Jetpack Navigation Compose
Dark/Light mode support with dynamic Material 3 theming

** Tech Stack

Language: Kotlin, C++
UI: Jetpack Compose + Material 3
Architecture: MVVM + Repository + UseCase
Dependency Injection: Hilt
Networking: Retrofit + OkHttp + Interceptor
Image Loading: Coil (AsyncImage)
Native Integration: JNI(NDK 29.0.13113456) + CMake(3.4.1)

** Setup Instructions
Clone this repository

git clone https://github.com/your_username/AjayNewsApp.git
cd AjayNewsApp

** Add Native Key
API key is fetched from C++:

std::string str = "<your_actual_api_key_here>";
Edit native-lib.cpp and replace with your real NewsAPI key.

Build the native lib
Android Studio handles it via CMake:
Ensure CMakeLists.txt and native-lib.cpp are in place.
Native library (native-lib) will be built and loaded automatically.

** Run the app
Open in Android Studio
Sync Gradle
Run on emulator or physical device

** Folder Structure
com.example.ajaynewsapp
├── ui            // Composables: HomeScreen, DetailScreen
├── network       // Retrofit API service, Interceptors
├── repository    // Interface and Implementation
├── viewmodel     // HomeViewModel
├── di            // Hilt modules: NetworkModule, AppModule
├── utils         // NativeLibraryData (JNI Bridge)
├── MainActivity.kt
├── App.kt       // Application class
├── theme         // ColorScheme and Typography

** Key Classes Explained
1. ApiKeyInterceptor
Adds the apiKey as a query parameter in every API request using OkHttp Interceptor.

2. NativeLibraryData
Kotlin bridge to fetch the API key from C++ using JNI.

3. NewsApiService
Retrofit interface to access NewsAPI's /everything?q=india endpoint.

4. TopHeadlinesRepository
Repository pattern to abstract the data layer.

5. TopHeadlinesUseCase
UseCase for business logic before ViewModel.

6. HomeViewModel
Handles UI state: loading, success, error, and selected article.

7. NewsNavGraph
Jetpack Compose Navigation with top app bar and back support.

8. HomeScreen and DetailScreen
HomeScreen is checking internet connection with retry button to load top news on home screen
Composable UIs to show article list and details with image + content.

** API Reference
Powered by https://newsapi.org
Endpoint used:
GET /v2/everything?q=india&apiKey=YOUR_API_KEY

** Future Improvements
Pagination
Categories filter
Room DB caching
Pull to refresh
Search functionality
