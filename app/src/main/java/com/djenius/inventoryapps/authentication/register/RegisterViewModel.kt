package com.djenius.inventoryapps.authentication.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    var firstName = ""
    var lastName = ""
    var email = ""
    var password = ""
    var passwordConfirmation = ""
    var aggreeWithTos = false

}