package com.example.apptfgandroid.module

import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.module.Qualifier.*
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.apptfgandroid.useCase.UseCaseManageContact
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<Set<UserDTO>> { mutableSetOf() }
    single(named(JWToken)) { MutableLiveData<String>().apply { value = "" } }

    single<DataSourceManageContacts> {DataSourceManageContacts()}
    single<RepositoryManageContacts>{RepositoryManageContacts(get())}
    single<UseCaseManageContact> { UseCaseManageContact(get()) }


    viewModel { AppViewModel(get()) }
}

enum class Qualifier{
    JWToken,
    BaseUrl,
}



