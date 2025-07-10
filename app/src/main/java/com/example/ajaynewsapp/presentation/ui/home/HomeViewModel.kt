package com.example.ajaynewsapp.presentation.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajaynewsapp.R
import com.example.ajaynewsapp.data.api.Article
import com.example.ajaynewsapp.domain.usecase.TopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topHeadlinesUseCase: TopHeadlinesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle: Article?
        get() = _selectedArticle.value

    fun setSelectedArticle(article: Article) {
        _selectedArticle.value = article
    }

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            try {
                val news = topHeadlinesUseCase()
                _uiState.value = UiState.Success(news)
            } catch (e: Exception) {
                val message = when (e) {
                    is IOException -> context.getString(R.string.network_error)
                    else -> context.getString(R.string.error_unknown)
                }
                _uiState.value = UiState.Error(message)
            }
        }
    }

    fun retry() {
        _uiState.value = UiState.Loading
        loadNews()
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val articles: List<Article>) : UiState()
        data class Error(val message: String) : UiState()
    }
}