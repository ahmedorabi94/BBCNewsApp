package com.example.bbcnewsapp.features.new_list.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bbcnewsapp.TestCoroutineRule
import com.example.bbcnewsapp.core.data.ApiService
import com.example.bbcnewsapp.core.data.Resource
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.core.domain.models.NewsResponse
import com.example.bbcnewsapp.core.domain.models.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var apiService: ApiService


    private lateinit var repositoryImpl: NewsRepositoryImpl


    @Before
    fun setup() {

        repositoryImpl = NewsRepositoryImpl(apiService)

    }

    @Test
    fun shouldGetNewsSuccessResponse() {

        val article =  Article(
            "BBC News", "", "",
            "", Source("", ""), "Police arrest", "", ""
        )
        val articleList = arrayListOf(
           article
        )

        val newsResponse = NewsResponse(articleList,"",1)


        val result1 = Resource.success(articleList)

        runBlocking {

            Mockito.doReturn(newsResponse)
                .`when`(apiService)
                .getNewsResponseAsync()

            val response = repositoryImpl.getNewsResponse().drop(1).first()

            Assert.assertEquals(response, result1)
            Assert.assertEquals(response.data?.get(0)?.author, result1.data?.get(0)?.author)
        }
    }


    @Test
    fun shouldGetListNewsFailureResponse() {

        val result1 = Resource.error<List<Article>>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getNewsResponseAsync()).willAnswer {
                throw IOException("Ooops")
            }

            val response = repositoryImpl.getNewsResponse().drop(1).first()

            Assert.assertEquals(response, result1)


        }
    }

}