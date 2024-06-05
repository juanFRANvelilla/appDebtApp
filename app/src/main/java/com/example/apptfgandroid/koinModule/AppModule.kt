package com.example.apptfgandroid.koinModule

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.dataSource.CurrentDebtsRemoteDataSource
import com.example.apptfgandroid.dataSource.LoginRemoteDataSource
import com.example.apptfgandroid.dataSource.ManageContactsRemoteDataSource
import com.example.apptfgandroid.dataSource.RegisterRemoteDataSource
import com.example.apptfgandroid.dataSource.MainMenuRemoteDataSource
import com.example.apptfgandroid.dataSource.SaveDebtRemoteDataSource
import com.example.apptfgandroid.repository.CurrentDebtsRepository
import com.example.apptfgandroid.repository.MainMenuRepository
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.LoginRepository
import com.example.apptfgandroid.repository.ManageContactsRepository
import com.example.apptfgandroid.repository.RepositoryRegister
import com.example.apptfgandroid.repository.SaveDebtRepository
import com.example.apptfgandroid.ui.screens.currentDebts.CurrentDebtsViewModel
import com.example.apptfgandroid.ui.screens.historyContactDebts.HistoryContactDebtsViewModel
import com.example.apptfgandroid.ui.screens.mainMenu.MainMenuViewModel
import com.example.apptfgandroid.ui.screens.register.RegisterViewModel
import com.example.apptfgandroid.ui.screens.login.LoginViewModel
import com.example.apptfgandroid.ui.screens.manageContacts.ManageContactsViewModel
import com.example.apptfgandroid.ui.screens.saveDebt.SaveDebtViewModel
import com.example.apptfgandroid.useCase.CurrentDebtsUseCase
import com.example.apptfgandroid.useCase.SaveDebtUseCase
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
    single<LoginRemoteDataSource> { LoginRemoteDataSource(get()) }
    single<LoginRepository> { LoginRepository(get()) }
    single<UseCaseLogin> { UseCaseLogin(get(), get()) }
    viewModel { LoginViewModel(get()) }

    //dependencias para el view model de register
    single<RegisterRemoteDataSource> { RegisterRemoteDataSource(get()) }
    single<RepositoryRegister> { RepositoryRegister(get()) }
    single<UseCaseRegister> { UseCaseRegister(get()) }
     viewModel { RegisterViewModel(get()) }


    //dependencias para el view model de manageContacts
    single<ManageContactsRemoteDataSource> { ManageContactsRemoteDataSource() }
    single<ManageContactsRepository>{ ManageContactsRepository(get()) }
    single<UseCaseManageContact>{ UseCaseManageContact(get(), get()) }
    viewModel { ManageContactsViewModel(get()) }

    //dependencias para el view model de mainMenu
    single<UseCaseMainMenu> { UseCaseMainMenu(get(), get()) }
    single<MainMenuRepository> { MainMenuRepository(get()) }
    single<MainMenuRemoteDataSource> { MainMenuRemoteDataSource() }
    viewModel { MainMenuViewModel(get(), get()) }

    //dependencias para el view model de saveDebt
    single<SaveDebtUseCase> { SaveDebtUseCase(get(), get()) }
    single<SaveDebtRepository> { SaveDebtRepository(get()) }
    single<SaveDebtRemoteDataSource> { SaveDebtRemoteDataSource() }
    viewModel { SaveDebtViewModel(get(), get()) }


    //dependencias para el view model de currentDebts
    single<CurrentDebtsUseCase> { CurrentDebtsUseCase(get(), get()) }
    single<CurrentDebtsRepository> { CurrentDebtsRepository(get()) }
    single<CurrentDebtsRemoteDataSource> { CurrentDebtsRemoteDataSource() }
    viewModel { CurrentDebtsViewModel(get()) }

    viewModel { HistoryContactDebtsViewModel(get())}


}




