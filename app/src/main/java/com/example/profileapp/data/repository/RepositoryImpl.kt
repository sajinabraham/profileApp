package com.example.profileapp.data.repository

import com.example.profileapp.data.remote.APIDetails
import com.example.profileapp.domain.model.Countries
import com.example.profileapp.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiDetails: APIDetails) : Repository {
    override suspend fun getPeople(): Response<Countries> = apiDetails.getPeople()
}