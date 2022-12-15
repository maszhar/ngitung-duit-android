package com.djenius.inventoryapps.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djenius.inventoryapps.authentication.AuthProto
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.Status
import io.grpc.StatusException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {
    val form = RegisterForm()
    val loading = MutableLiveData(false)
    val success = MutableLiveData(false)
    val error = MutableLiveData<RegisterError?>(null)
    val errorMsg = MutableLiveData<String?>(null)

    fun clearError() {
        error.postValue(null)
    }

    fun clearErrorMsg() {
        errorMsg.postValue(null)
    }

    fun register() {
        if (form.validateAllInputs()) {
            loading.postValue(true)

            viewModelScope.launch {
                try {
                    val result = repository.register(
                        form.firstName,
                        form.lastName,
                        form.email,
                        form.password,
                        form.agreeWithTos
                    )
                    when (result.result) {
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
                } catch (e: StatusException) {
                    when (e.status.code) {
                        Status.UNAVAILABLE.code ->
                            error.postValue(RegisterError.UNAVAILABLE)
                        else ->
                            errorMsg.postValue(e.status.toString())
                    }
                } catch (e: Exception) {
                    errorMsg.postValue(e.toString())
                } finally {
                    loading.postValue(false)
                }
            }
        }
    }
}

enum class RegisterError {
    UNAVAILABLE
}