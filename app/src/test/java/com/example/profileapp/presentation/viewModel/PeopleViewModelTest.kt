package com.example.profileapp.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.profileapp.domain.model.Countries
import com.example.profileapp.domain.model.CountriesItem
import com.example.profileapp.domain.model.Currency
import com.example.profileapp.domain.model.Language
import com.example.profileapp.domain.repository.Repository
import com.example.utils.NetworkResult
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
    fun `People data from response when Success Then result is Empty`() = runBlocking {
        val response = Countries()
        whenever(repository.getPeople()).thenReturn(Response.success(response))

        viewModel.getPeople()

        viewModel.peopleResponse.asLiveData().observeForever {
            assertEquals((it as NetworkResult.Success).data, response)
        }

    }

    @Test
    fun `People data from response when error then return Error`() = runBlocking {
        whenever(repository.getPeople()).thenReturn(Response.error(404, "Error ".toResponseBody()))
        viewModel.getPeople()
        viewModel.peopleResponse.asLiveData().observeForever {
            assertEquals((it as NetworkResult.Error).message, "")
        }

    }

    @Test
    fun `People data from response When success Then return all data`() = runBlocking {
        val people = arrayListOf(
            CountriesItem(
                capital = "Kabul",
                code = "AF",
                currency = Currency("AFN", "Afghan afghani", "$"),
                demonym = "red",
                flag = "https://restcountries.eu/data/afg.svg",
                language = Language(code = "ps", iso639_2 = "Pashto", name = "", nativeName = ""),
                name = "Afghanistan",
                region = "AS",
            )
        )

        Mockito.`when`(repository.getPeople())
            .thenReturn(Response.success(people) as Response<Countries>)
        viewModel.getPeople()
        viewModel.peopleResponse.asLiveData().observeForever {
            assertEquals((it as NetworkResult.Success).data, people)
        }

    }
}