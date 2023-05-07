package com.example.bbcnewsapp.features.news_details

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.core.domain.models.Source
import org.junit.Test
import org.junit.runner.RunWith
import com.example.bbcnewsapp.R
import com.example.bbcnewsapp.features.waitFor


@RunWith(AndroidJUnit4::class)
class NewsDetailsFragmentTest {


    @Test
    fun testDetailsFragment() {

        val article = Article(
            "BBC News",
            "A Canadian man has been arrested in British Columbia for opening a mobile shop to sell cocaine",
            "Jerry Martin of Vancouver has said he wanted to sell drugs without fentanyl to help prevent harms.",
            "2023-05-05T17:07:15.8602783Z",
            Source("", ""),
            "Police arrest man for opening store selling hard drugs in Canada",
            "http://www.bbc.co.uk/news/world-us-canada-65487199",
            "https://ichef.bbci.co.uk/news/1024/branded_news/12C6E/production/_129601967_gettyimages-1240791902.jpg"
        )

        val bundle = Bundle()
        bundle.putParcelable(ARTICLE_ARG_PARAM1, article)


        val scenario = launchFragmentInContainer<NewsDetailsFragment>(bundle)

        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(isRoot()).perform(waitFor(2000))


        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.descriptionTV)).check(matches(isDisplayed()))
        onView(withId(R.id.publishTv)).check(matches(isDisplayed()))
        onView(withId(R.id.contentTV)).check(matches(isDisplayed()))
        onView(withId(R.id.authorTV)).check(matches(isDisplayed()))


        onView(withId(R.id.titleTV)).check(matches(withText(article.title)))
        onView(withId(R.id.descriptionTV)).check(matches(withText(article.description)))
        onView(withId(R.id.publishTv)).check(matches(withText(article.publishedAt)))
        onView(withId(R.id.contentTV)).check(matches(withText(article.content)))
        onView(withId(R.id.authorTV)).check(matches(withText(article.author)))




    }

}