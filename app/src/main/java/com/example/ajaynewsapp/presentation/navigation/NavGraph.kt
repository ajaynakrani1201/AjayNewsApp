package com.example.ajaynewsapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ajaynewsapp.R
import com.example.ajaynewsapp.presentation.ui.detail.DetailScreen
import com.example.ajaynewsapp.presentation.ui.home.HomeScreen
import com.example.ajaynewsapp.presentation.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavGraph(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current
    var topBarTitle by remember { mutableStateOf(context.getString(R.string.app_name)) }
    var showBackArrow by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topBarTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                                contentDescription = stringResource(R.string.back_desc),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                val viewModel: HomeViewModel = hiltViewModel()
                showBackArrow = false
                topBarTitle = context.getString(R.string.home_screen_title)

                HomeScreen(navController = navController, viewModel = viewModel)
            }

            composable("detail") {
                val parentEntry = remember { navController.getBackStackEntry("home") }
                val viewModel: HomeViewModel = hiltViewModel(parentEntry)
                val article = viewModel.selectedArticle
                if (article != null) {
                    showBackArrow = true
                    topBarTitle = article.title ?: context.getString(R.string.no_title_available)

                    DetailScreen(article = article)
                }
            }
        }
    }
}
