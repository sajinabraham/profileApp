package com.example.profileapp.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.profileapp.domain.model.PeopleModel
import com.example.profileapp.domain.model.PeopleResult
import com.example.profileapp.domain.repository.Repository
import com.example.profileinfo.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class PeopleViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    private lateinit var viewModel: PeopleViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = PeopleViewModel(repository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `People data from response when Success Then result is Empty`()= runBlocking{
        val response = PeopleModel()
        whenever(repository.getPeople()).thenReturn(Response.success(response))

        viewModel.getPeople()

        viewModel.peopleResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Success).data,response)
        }

    }

    @Test
    fun `People data from response when error then return Error`()= runBlocking{
        whenever(repository.getPeople()).thenReturn(Response.error(404,"Error ".toResponseBody()))
        viewModel.getPeople()
        viewModel.peopleResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Error).message,"")
        }

    }

    @Test
    fun `People data from response When success Then return all data`()= runBlocking{
        val people = arrayListOf(
            PeopleResult(avatar = "",
                createdAt = "",
                email = "sajinabraham@icloud.com",
                favouriteColor = "red",
                firstName = "sajin",
                fromName = "",
                id = "3",
                jobtitle = "software developer",
                lastName = "Abraham",
                to= "")
        )

        Mockito.`when`(repository.getPeople()).thenReturn(Response.success(people) as Response<PeopleModel>)
        viewModel.getPeople()
        viewModel.peopleResponse.asLiveData().observeForever{
            assertEquals((it as NetworkResult.Success).data,people)
        }

    }
}