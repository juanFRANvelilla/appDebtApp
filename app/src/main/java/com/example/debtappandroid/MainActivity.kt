package com.example.debtappandroid

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.koinModule.appModule
import com.example.debtappandroid.navigation.AppNavigation
import com.example.debtappandroid.ui.theme.AppTfgAndroidTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTfgAndroidTheme(
                darkTheme = false
            ) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val manageTokenViewModel = getViewModel<ManageTokenViewModel>()
                    AppNavigation(manageTokenViewModel)
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
