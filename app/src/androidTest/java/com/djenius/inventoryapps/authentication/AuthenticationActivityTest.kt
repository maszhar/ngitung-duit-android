package com.djenius.inventoryapps.authentication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.djenius.inventoryapps.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AuthenticationActivityTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<AuthenticationActivity>()

    @Test
    fun ensureInputsRendered() {
        onView(withId(R.id.firstname_et)).check(matches(isDisplayed()))
        onView(withId(R.id.lastname_et)).check(matches(isDisplayed()))
        onView(withId(R.id.email_et)).check(matches(isDisplayed()))
        onView(withId(R.id.password_et)).check(matches(isDisplayed()))
        onView(withId(R.id.password_confirm_et)).check(matches(isDisplayed()))

        onView(withId(R.id.tos_cb)).check(matches(isDisplayed()))
        onView(withId(R.id.tos_cb)).check(matches(withText(R.string.agree_with_tos)))

        onView(withId(R.id.register_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.register_btn)).check(matches(withText(R.string.register)))
    }

    @Test
    fun showBlankInputErrors() {
        onView(withId(R.id.register_btn)).perform(click())

        onView(withId(R.id.firstname_et)).check(matches(hasErrorText("First Name cannot be blank")))
        onView(withId(R.id.lastname_et)).check(matches(hasErrorText("Last Name cannot be blank")))
        onView(withId(R.id.email_et)).check(matches(hasErrorText("Email cannot be blank")))
        onView(withId(R.id.password_et)).check(matches(hasErrorText("Password cannot be blank")))
        onView(withId(R.id.password_confirm_et)).check(matches(hasErrorText("Password Confirmation cannot be blank")))
    }

    @Test
    fun showInvalidEmailError() {
        onView(withId(R.id.email_et)).perform(typeText("email"))
        onView(withId(R.id.email_et)).check(matches(hasErrorText("Email must be an email")))
    }
}