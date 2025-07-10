package com.example.ajaynewsapp.presentation.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ajaynewsapp.R
import com.example.ajaynewsapp.data.api.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    article: Article
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.content ?: stringResource(R.string.no_content_available),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


