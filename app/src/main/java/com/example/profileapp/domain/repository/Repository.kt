package com.example.profileapp.domain.repository

import com.example.profileapp.domain.model.Countries
import retrofit2.Response

interface Repository {
    suspend fun getPeople(): Response<Countries>
}