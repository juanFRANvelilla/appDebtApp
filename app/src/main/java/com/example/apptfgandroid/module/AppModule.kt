package com.example.apptfgandroid.module

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.dataSource.DataSourceLogin
import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.dataSource.DataSourceRegister
import com.example.apptfgandroid.module.Qualifier.*
import com.example.apptfgandroid.repository.RepositoryLogin
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.apptfgandroid.repository.RepositoryRegister
import com.example.apptfgandroid.ui.screens.Login.LoginViewModel
import com.example.apptfgandroid.ui.screens.ManageContacts.ManageContactsViewModel
import com.example.apptfgandroid.ui.screens.Register.RegisterViewModel
import com.example.apptfgandroid.useCase.UseCaseLogin
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.useCase.UseCaseRegister
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single(named(JWToken)) { MutableLiveData<String>().apply { value = "" } }
    viewModel { AppViewModel(get(named(JWToken))) }



    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //dependencias para el view model de login
    val context: Context = getKoin().get()
    single { context }
    single{ preferencesDataStore(name = "preferences") }
    single<DataSourceLogin> { DataSourceLogin(get()) }
    single<RepositoryLogin> { RepositoryLogin(get(), get()) }
    single<UseCaseLogin> { UseCaseLogin(get()) }
    viewModel { LoginViewModel(get()) }

    //dependencias para el view model de register
    single<DataSourceRegister> { DataSourceRegister(get()) }
    single<RepositoryRegister> { RepositoryRegister(get()) }
    single<UseCaseRegister> { UseCaseRegister(get()) }
     viewModel { RegisterViewModel(get()) }


    //dependencias para el view model de manageContacts
    single<DataSourceManageContacts> { DataSourceManageContacts(get()) }
    single<RepositoryManageContacts>{ RepositoryManageContacts(get()) }
    single<UseCaseManageContact>{ UseCaseManageContact(get()) }
    viewModel { ManageContactsViewModel(get()) }

}

enum class Qualifier{
    JWToken,
}



