package com.example.debtappandroid.repository

import com.example.debtappandroid.dataSource.RegisterRemoteDataSource
import com.example.debtappandroid.models.user.CreateUserDTO
import com.example.debtappandroid.models.access.PhoneValidationDTO
import com.example.debtappandroid.models.access.SmsCode
import com.example.tfgapp.models.ServerResponseDTO

class RepositoryRegister(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) {
    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO): ServerResponseDTO = registerRemoteDataSource.confirmPhone(phoneValidationDTO)

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO = registerRemoteDataSource.validatePhone(createUserDTO)

    suspend fun sendSmsCode(smsCode: SmsCode) = registerRemoteDataSource.sendSmsCode(smsCode)

}