package com.example.apptfgandroid.module

import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.data.Preferences.JWToken.PreferenceToken
import com.example.apptfgandroid.data.Preferences.JWToken.PreferenceTokenImpl
import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.module.Qualifier.*
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.apptfgandroid.repository.preferences.JWTokenRepository
import com.example.apptfgandroid.repository.preferences.JWTokenRepositoryImpl
import com.example.apptfgandroid.ui.screens.ManageContacts.ManageContactsViewModel
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.useCase.preferences.GetToken
import com.example.apptfgandroid.useCase.preferences.SaveToken
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<Set<UserDTO>> { mutableSetOf() }
    single(named(JWToken)) { MutableLiveData<String>().apply { value = "" } }

    single<DataSourceManageContacts> { DataSourceManageContacts(get()) }
    single<RepositoryManageContacts>{ RepositoryManageContacts(get()) }
    single<UseCaseManageContact>{ UseCaseManageContact(get()) }




    single<PreferenceToken> { PreferenceTokenImpl(androidContext()) }
    single<JWTokenRepository> { JWTokenRepositoryImpl(get()) }
    single { GetToken(get()) }
    single { SaveToken(get()) }


    viewModel { AppViewModel(get(named(JWToken))) }
    viewModel { ManageContactsViewModel(get()) }
}

enum class Qualifier{
    JWToken,
    UseCase,
}



