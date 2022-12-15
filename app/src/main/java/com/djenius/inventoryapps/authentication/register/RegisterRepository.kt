package com.djenius.inventoryapps.authentication.register

import com.djenius.inventoryapps.authentication.AuthProto
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        agreeTos: Boolean
    ): AuthProto.RegisterResponse {
        val request = AuthProto.RegisterRequest.newBuilder()
            .setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setPassword(password)
            .setAgreeTos(agreeTos)
            .build()

        return registerService.register(request)
    }
}