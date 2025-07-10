package com.example.ajaynewsapp.utils

class NativeLibraryData {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiKey(): String
}