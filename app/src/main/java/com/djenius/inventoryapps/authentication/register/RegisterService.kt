package com.djenius.inventoryapps.authentication.register

import com.djenius.inventoryapps.authentication.AuthProto
import com.djenius.inventoryapps.utils.GrpcProvider
import javax.inject.Inject

class RegisterService @Inject constructor(
    private val grpcProvider: GrpcProvider
) {
    suspend fun register(request: AuthProto.RegisterRequest): AuthProto.RegisterResponse {
        val stub = grpcProvider.getAuthStub()
        return stub.register(request)
    }
}