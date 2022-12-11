package com.djenius.inventoryapps.utils

object Validators {

    val NOT_BLANK = { identifier: String, mValue: Any ->
        val value: String = mValue.toString()
        if (value.isBlank()) {
            "$identifier cannot be blank"
        } else {
            null
        }
    }

    val EMAIL = { identifier: String, mValue: Any ->
        val emailPattern =
            "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

        val value = mValue.toString()
        if (!value.matches(Regex(emailPattern))) {
            "$identifier must be an email"
        } else {
            null
        }
    }

    fun BE_TRUTHY(failMessage: String) = {_: String, mValue: Any ->
        val value = if(mValue is Boolean) {
            mValue
        } else {
            false
        }
        if(!value) {
            failMessage
        } else {
            null
        }
    }

    fun <T> validate(identifier: String, value: T, rules: Array<(String, T) -> String?>): String? {
        for (rule in rules) {
            val result = rule(identifier, value)
            if (result != null) {
                return result
            }
        }
        return null
    }
}