package com.example.profileapp.data.remote


import com.example.profileapp.domain.model.Countries
import retrofit2.Response
import retrofit2.http.GET

/*
* Created by Sajin Abraham on 17/07/2023 01:07
*/

interface APIDetails {
    @GET("countries.json")
    suspend fun getPeople(): Response<Countries>
}
