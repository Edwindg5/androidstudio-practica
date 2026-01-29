// MainActivity.kt
package com.edwindiaz.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.edwindiaz.myapplication.core.di.AppContainer
import com.edwindiaz.myapplication.features.jsonplaceholder.di.JsonPlaceholderModule
import com.edwindiaz.myapplication.features.jsonplaceholder.presentation.screens.PostsScreen
import com.edwindiaz.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(applicationContext)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val jsonPlaceholderModule = JsonPlaceholderModule(appContainer)
                    val factory = jsonPlaceholderModule.providePostsViewModelFactory()

                    PostsScreen(factory = factory)
                }
            }
        }
    }
}