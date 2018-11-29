package ag.plazz.standardtest


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.startsWith

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FunctionalityInstrumentedTest {

    private lateinit var startString: String
    private lateinit var stringToBeTyped: String
    private lateinit var secondstringToBeTyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initData() {
        startString = "Push the button"
        stringToBeTyped = "Setz dich an deinen plazz"
        secondstringToBeTyped = "Ich will MEA"
    }

    @Test
    fun enterStringAndCommit_MainActivity() {
        onView(withId(R.id.dataLbl)).check(matches(withText(startString)))
        onView(withId(R.id.dataInput)).perform(typeText(stringToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.setDataBtn)).perform(click())
        onView(withId(R.id.dataLbl)).check(matches(withText(stringToBeTyped)))
    }

    @Test
    fun enterStringAndRedirect_DetailActivity() {
        onView(withId(R.id.dataLbl)).check(matches(withText(startString)))
        onView(withId(R.id.dataInput)).perform(typeText(secondstringToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.setDataBtn)).perform(click())
        onView(withId(R.id.dataLbl)).check(matches(withText(secondstringToBeTyped)))
        onView(withId(R.id.openDetailBtn)).perform(click())
        onView(withId(R.id.resultDataLabel)).check(matches(withText(secondstringToBeTyped)))
    }
}
