package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.apptfgandroid.repository.RepositoryRegister
import com.example.tfgapp.models.ServerResponseDTO

class UseCaseRegister(
    private val repositoryRegister: RepositoryRegister
) {
    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO): ServerResponseDTO = repositoryRegister.confirmPhone(phoneValidationDTO)

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO = repositoryRegister.validatePhone(createUserDTO)

}