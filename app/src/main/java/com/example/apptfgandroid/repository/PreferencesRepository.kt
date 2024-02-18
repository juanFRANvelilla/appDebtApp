//package com.example.apptfgandroid.repository
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import com.example.apptfgandroid.preference.PreferenceKey
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class PreferencesRepository(
//    private val context: Context
//) {
//    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "preferences")
//    suspend fun saveToken(token: String){
//        context.datastore.edit { preferences ->
//            preferences[PreferenceKey.TOKEN] = token
//        }
//    }
//
//    fun getNameFlow(): Flow<String?> {
//        val nameFlow = context.datastore.data.map { preferences ->
//            preferences[PreferenceKey.TOKEN]
//        }
//        return nameFlow
//    }
//}