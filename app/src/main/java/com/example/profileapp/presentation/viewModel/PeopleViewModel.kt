package com.example.profileapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.NetworkResult
import com.example.profileapp.domain.model.PeopleModel
import com.example.profileapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _peopleResponse: MutableStateFlow<NetworkResult<PeopleModel>> =
        MutableStateFlow(NetworkResult.Loading())

    val peopleResponse: StateFlow<NetworkResult<PeopleModel>> get() = _peopleResponse

    fun getPeople() = viewModelScope.launch {
        getPeopleCall()
    }

    private suspend fun getPeopleCall() {
        try {
            val response = repository.getPeople()
            _peopleResponse.value = handlePeopleResponse(response)
        } catch (e: Exception) {
            _peopleResponse.value = NetworkResult.Error("People not found.")
        }
    }

    private fun handlePeopleResponse(response: Response<PeopleModel>): NetworkResult<PeopleModel> {

        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }

            response.body()!!.isEmpty() -> {
                NetworkResult.Error("People not found.")
            }

            response.isSuccessful -> {
                NetworkResult.Success(response.body())
            }

            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}