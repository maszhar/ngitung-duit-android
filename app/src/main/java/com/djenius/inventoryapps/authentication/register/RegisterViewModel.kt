package com.djenius.inventoryapps.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djenius.inventoryapps.authentication.AuthProto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var service: RegisterService
    val form = RegisterForm()
    val loading = MutableLiveData(false)
    val success = MutableLiveData(false)
    val errorMsg = MutableLiveData<String?>(null)

    fun register() {
        if (form.validateAllInputs()) {
            loading.postValue(true)

            val request = AuthProto.RegisterRequest.newBuilder()
                .setFirstName(form.firstName)
                .setLastName(form.lastName)
                .setEmail(form.email)
                .setPassword(form.password)
                .setAgreeTos(form.agreeWithTos)
                .build()

            viewModelScope.launch {
                val result = service.register(request)
                when(result.result) {
                    AuthProto.Result.INVALID_FIELDS -> {
                        errorMsg.postValue("Please update the application")
                    }
                    AuthProto.Result.SUCCESS -> {
                        success.postValue(true)
                    }
                    else -> {
                        errorMsg.postValue("Error from server")
                    }
                }
                loading.postValue(false)
            }
        }
    }
}