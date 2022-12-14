package com.djenius.inventoryapps.utils

import com.djenius.inventoryapps.BuildConfig
import com.djenius.inventoryapps.authentication.AuthenticationGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GrpcProvider @Inject constructor() {
    private val authChannel by lazy {
        createChannel(BuildConfig.AUTH_HOST)
    }

    private fun createChannel(address: String): ManagedChannel {
        return ManagedChannelBuilder.forAddress(address, 443)
            .executor(Dispatchers.IO.asExecutor())
            .useTransportSecurity()
            .build()
    }

    fun getAuthStub(): AuthenticationGrpc.AuthenticationBlockingStub {
        return AuthenticationGrpc.newBlockingStub(authChannel)
    }
}