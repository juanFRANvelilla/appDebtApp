package com.example.apptfgandroid.models.user

data class UserDTO (
    val username: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = ""
){
    companion object {
        fun empty(): UserDTO {
            return UserDTO(
                username = "",
                firstName = "",
                lastName = "",
                email = ""
            )
        }
    }
}
