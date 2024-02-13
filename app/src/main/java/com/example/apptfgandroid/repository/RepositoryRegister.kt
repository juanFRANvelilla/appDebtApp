package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceRegister
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.tfgapp.models.ServerResponseDTO

class RepositoryRegister(
    private val dataSourceRegister: DataSourceRegister
) {
    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO){
        dataSourceRegister.confirmPhone(phoneValidationDTO)
    }

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO = dataSourceRegister.validatePhone(createUserDTO)
}