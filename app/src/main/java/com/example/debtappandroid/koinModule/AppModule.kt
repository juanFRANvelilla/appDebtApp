package com.example.debtappandroid.koinModule

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.dataSource.CurrentDebtsRemoteDataSource
import com.example.debtappandroid.dataSource.LoginRemoteDataSource
import com.example.debtappandroid.dataSource.ManageContactsRemoteDataSource
import com.example.debtappandroid.dataSource.RegisterRemoteDataSource
import com.example.debtappandroid.dataSource.MainMenuRemoteDataSource
import com.example.debtappandroid.dataSource.SaveDebtRemoteDataSource
import com.example.debtappandroid.repository.CurrentDebtsRepository
import com.example.debtappandroid.repository.MainMenuRepository
import com.example.debtappandroid.repository.LoginRepository
import com.example.debtappandroid.repository.ManageContactsRepository
import com.example.debtappandroid.repository.RepositoryRegister
import com.example.debtappandroid.repository.SaveDebtRepository
import com.example.debtappandroid.ui.screens.currentDebts.CurrentDebtsViewModel
import com.example.debtappandroid.ui.screens.historyContactDebts.HistoryContactDebtsViewModel
import com.example.debtappandroid.ui.screens.mainMenu.MainMenuViewModel
import com.example.debtappandroid.ui.screens.register.RegisterViewModel
import com.example.debtappandroid.ui.screens.login.LoginViewModel
import com.example.debtappandroid.ui.screens.manageContacts.ManageContactsViewModel
import com.example.debtappandroid.ui.screens.saveDebt.SaveDebtViewModel
import com.example.debtappandroid.useCase.CurrentDebtsUseCase
import com.example.debtappandroid.useCase.SaveDebtUseCase
import com.example.debtappandroid.useCase.UseCaseLogin
import com.example.debtappandroid.useCase.UseCaseMainMenu
import com.example.debtappandroid.useCase.UseCaseManageContact
import com.example.debtappandroid.useCase.UseCaseRegister
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { MutableLiveData<String>().apply { value = "" } }
    viewModel { ManageTokenViewModel(get()) }


    single(named("urlFastApi")) {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8003/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("urlBackend")) {
        Retrofit.Builder()
            .baseUrl("https://tfg.api.juanfranciscoperez.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //dependencias para el view model de login
    single{ preferencesDataStore(name = "preferences") }
    single<LoginRemoteDataSource> { LoginRemoteDataSource(get(named("urlBackend"))) }
    single<LoginRepository> { LoginRepository(get()) }
    single<UseCaseLogin> { UseCaseLogin(get(), get()) }
    viewModel { LoginViewModel(get()) }



    //dependencias para el view model de register
    single<RegisterRemoteDataSource> {
        val urlFastApi: Retrofit = get(named("urlFastApi"))
        val urlBackend: Retrofit = get(named("urlBackend"))
        RegisterRemoteDataSource(urlFastApi, urlBackend)
    }
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

    viewModel { _parameters -> HistoryContactDebtsViewModel(get(), _parameters[0])}


}




