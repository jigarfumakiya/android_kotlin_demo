package com.app.android_test

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.android_test.features.welcome.WelcomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class WelcomeFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(HiltTestActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testShowSnackbarWhenNameIsEmpty() {
        launchFragmentInHiltContainer<WelcomeFragment>(Bundle(), R.style.Theme_Android_test) {}
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.name)))
    }

    @Test
    fun testSaveUserDetailsNavigatesToHome(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val scenario = launchFragmentInHiltContainer<WelcomeFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.nameEditText)).perform(typeText("John "), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.nav_home)
    }
}
