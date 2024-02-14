package com.example.apptfgandroid

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
import androidx.lifecycle.lifecycleScope
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.module.appModule
import com.example.apptfgandroid.navigation.AppNavigation
import com.example.apptfgandroid.preference.PreferenceKey
import com.example.apptfgandroid.ui.screens.Login.LoginViewModel
import com.example.apptfgandroid.ui.theme.AppTfgAndroidTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

//    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "preferences")













    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        startKoin{
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
        super.onCreate(savedInstanceState)
        val viewModel: LoginViewModel = getViewModel()
        var darkMode: Boolean = false

        lifecycleScope.launch {
            darkMode = viewModel.getDarkModeValue()
        }
        

//        getDarkMode {  darkMode = it }
        setContent {
            AppTfgAndroidTheme(
                darkTheme = darkMode
            ) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val appViewModel = getViewModel<AppViewModel>()
                    AppNavigation(appViewModel)
                }

            }
        }


    }
    

}
