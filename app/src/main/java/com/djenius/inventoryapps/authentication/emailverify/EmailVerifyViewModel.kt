package com.djenius.inventoryapps.authentication.emailverify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailVerifyViewModel @Inject constructor() : ViewModel() {
    val registeredEmail = MutableLiveData("")

    fun setRegisteredEmail(email: String) {
        registeredEmail.postValue(email)
    }
}