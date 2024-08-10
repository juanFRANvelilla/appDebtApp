package com.example.debtappandroid.useCase

import com.example.debtappandroid.models.user.CreateUserDTO
import com.example.debtappandroid.models.access.PhoneValidationDTO
import com.example.debtappandroid.models.access.SmsCode
import com.example.debtappandroid.repository.RepositoryRegister
import com.example.tfgapp.models.ServerResponseDTO

class UseCaseRegister(
    private val repositoryRegister: RepositoryRegister
) {
    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO): ServerResponseDTO = repositoryRegister.confirmPhone(phoneValidationDTO)

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO = repositoryRegister.validatePhone(createUserDTO)

    suspend fun sendSmsCode(smsCode: SmsCode) = repositoryRegister.sendSmsCode(smsCode)

}