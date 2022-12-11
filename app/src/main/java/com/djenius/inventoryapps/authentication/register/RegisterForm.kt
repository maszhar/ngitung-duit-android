package com.djenius.inventoryapps.authentication.register

import androidx.lifecycle.MutableLiveData
import com.djenius.inventoryapps.utils.Validators

class RegisterForm() {
    var firstName = ""
    var lastName = ""
    var email = ""
    var password = ""
    var passwordConfirmation = ""
    var agreeWithTos = false

    val firstNameError = MutableLiveData<String?>(null)
    val lastNameError = MutableLiveData<String?>(null)
    val emailError = MutableLiveData<String?>(null)
    val passwordError = MutableLiveData<String?>(null)
    val passwordConfirmationError = MutableLiveData<String?>(null)
    val agreeWithTosError = MutableLiveData<String?>(null)

    fun validateFirstName(value: CharSequence? = null): Boolean {
        val result = Validators.validate(
            "First Name", value?.toString() ?: firstName, arrayOf(
                Validators.NOT_BLANK
            )
        )
        firstNameError.postValue(result)
        return result == null
    }

    fun validateLastName(value: CharSequence? = null): Boolean {
        val result = Validators.validate(
            "Last Name", value?.toString() ?: lastName, arrayOf(
                Validators.NOT_BLANK
            )
        )
        lastNameError.postValue(result)
        return result == null
    }

    fun validateEmail(value: CharSequence? = null): Boolean {
        val result = Validators.validate(
            "Email", value?.toString() ?: email, arrayOf(
                Validators.NOT_BLANK,
                Validators.EMAIL
            )
        )
        emailError.postValue(result)
        return result == null
    }

    fun validatePassword(value: CharSequence? = null): Boolean {
        val result = Validators.validate(
            "Password", value?.toString() ?: password, arrayOf(
                Validators.NOT_BLANK
            )
        )
        passwordError.postValue(result)
        return result == null
    }

    fun validatePasswordConfirmation(value: CharSequence? = null): Boolean {
        val result = Validators.validate(
            "Password Confirmation", value?.toString() ?: passwordConfirmation, arrayOf(
                Validators.NOT_BLANK
            )
        )
        passwordConfirmationError.postValue(result)
        return result == null
    }

    fun validateTosAgreement(value: Boolean? = null): Boolean {
        val result = Validators.validate(
            "Password Confirmation", value ?: agreeWithTos, arrayOf(
                Validators.BE_TRUTHY("You must agree the terms of service to register")
            )
        )
        agreeWithTosError.postValue(result)
        return result == null
    }

    fun validateAllInputs(): Boolean {
        return (
                validateFirstName()
                        and validateLastName()
                        and validateEmail()
                        and validatePassword()
                        and validatePasswordConfirmation()
                        and validateTosAgreement()
                )
    }
}