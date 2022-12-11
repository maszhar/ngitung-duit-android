package com.djenius.inventoryapps.utils

import com.github.javafaker.Faker
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ValidatorsTest {
    private val faker = Faker()

    @Test
    fun notBlank_GivenNonBlankString_ReturnsNull() {
        val result = Validators.NOT_BLANK("identifier", "Non blank string")
        assertNull(result)
    }

    @Test
    fun notBlank_GivenBlankString_ReturnsError() {
        val result = Validators.NOT_BLANK("identifier", "")
        assertNotNull(result)
    }

    @Test
    fun notBlank_GivenBlankString_ReturnsErrorWithIdentifier() {
        val identifier = faker.name().firstName()
        val result = Validators.NOT_BLANK(identifier, "")
        assertThat(result).contains(identifier)
    }

    @Test
    fun email_GivenEmailString_ReturnsNull() {
        val result = Validators.EMAIL("identifier", "alpha@example.com")
        assertNull(result)
    }

    @Test
    fun email_GivenInvalidEmailString_ReturnsError() {
        val result = Validators.EMAIL("identifier", "alpha12yyy")
        assertNotNull(result)
    }

    @Test
    fun validate_GivenValidCondition_ReturnsNull() {
        val result = Validators.validate("identifier", "Valid string", arrayOf(
            Validators.NOT_BLANK
        ))
        assertNull(result)
    }

    @Test
    fun validate_GivenInvalidCondition_ReturnsFirstError() {
        val identifier = "identifier"
        val payload = ""
        val notBlankError = Validators.NOT_BLANK(identifier, payload)
        val result = Validators.validate(identifier, payload, arrayOf(
            Validators.NOT_BLANK
        ))

        assertNotNull(result)
        assertThat(result).isEqualTo(notBlankError)
    }
}