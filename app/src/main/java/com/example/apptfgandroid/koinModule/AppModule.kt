package com.example.apptfgandroid.koinModule

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.dataSource.DataSourceLogin
import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.dataSource.DataSourceRegister
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.RepositoryLogin
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.apptfgandroid.repository.RepositoryRegister
import com.example.apptfgandroid.ui.screens.mainMenu.MainMenuViewModel
import com.example.apptfgandroid.ui.screens.Register.RegisterViewModel
import com.example.apptfgandroid.ui.screens.Login.LoginViewModel
import com.example.apptfgandroid.ui.screens.ManageContacts.ManageContactsViewModel
import com.example.apptfgandroid.useCase.UseCaseLogin
import com.example.apptfgandroid.useCase.UseCaseMainMenu
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.useCase.UseCaseRegister
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { MutableLiveData<String>().apply { value = "" } }
    viewModel { ManageTokenViewModel(get()) }


    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //dependencias para el preferenceRepository
//    val context: Context = getKoin().get()
//    single { context }
//    single<PreferencesRepository> {PreferencesRepository(get())}


    //dependencias para el view model de login
    single{ preferencesDataStore(name = "preferences") }
    single<DataSourceLogin> { DataSourceLogin(get()) }
    single<RepositoryLogin> { RepositoryLogin(get()) }
    single<UseCaseLogin> { UseCaseLogin(get(), get()) }
    viewModel { LoginViewModel(get()) }

    //dependencias para el view model de register
    single<DataSourceRegister> { DataSourceRegister(get()) }
    single<RepositoryRegister> { RepositoryRegister(get()) }
    single<UseCaseRegister> { UseCaseRegister(get()) }
     viewModel { RegisterViewModel(get()) }


    //dependencias para el view model de manageContacts
    single<DataSourceManageContacts> { DataSourceManageContacts() }
    single<RepositoryManageContacts>{ RepositoryManageContacts(get()) }
    single<UseCaseManageContact>{ UseCaseManageContact(get(), get()) }
    viewModel { ManageContactsViewModel(get()) }

    //dependencias para el view model de mainMenu
    single<UseCaseMainMenu> { UseCaseMainMenu(get()) }
    viewModel { MainMenuViewModel(get(), get()) }


}




