package com.example.bbcnewsapp.features.new_list.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bbcnewsapp.MainActivity
import com.example.bbcnewsapp.R
import com.example.bbcnewsapp.features.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsListFragmentTest {


    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()


    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun testNewsListFragment() {


        onView(withId(R.id.recycler_view_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        onView(withId(R.id.recycler_view_main))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, click()))



        onView(withId(R.id.titleTV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.descriptionTV)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }


}