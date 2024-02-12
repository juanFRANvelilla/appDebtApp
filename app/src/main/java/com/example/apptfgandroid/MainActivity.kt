package com.example.apptfgandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.module.appModule
import com.example.apptfgandroid.navigation.AppNavigation
import com.example.apptfgandroid.ui.theme.AppTfgAndroidTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin
import java.util.prefs.Preferences

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTfgAndroidTheme(
                darkTheme = false
            ) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val appViewModel = getViewModel<AppViewModel>()
                    lifecycleScope.launch {
                        val deferredToken = async { appViewModel.getToken() }
                        val token = deferredToken.await()
                        println("token $token")
                    }


                    appViewModel.setToken("tokenintroducido")

                    lifecycleScope.launch {
                        val deferredToken = async { appViewModel.getToken() }
                        val token = deferredToken.await()
                        println("token2 $token")
                    }


                    AppNavigation(appViewModel)
                }

            }
        }

        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

}
