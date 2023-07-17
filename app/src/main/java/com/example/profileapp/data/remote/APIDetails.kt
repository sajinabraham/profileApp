package com.example.profileapp.data.remote


import com.example.profileapp.domain.model.PeopleModel
import retrofit2.Response
import retrofit2.http.GET

/*
* Created by Sajin Abraham on 17/07/2023 01:07
*/

interface APIDetails {
    @GET("people")
    suspend fun getPeople(): Response<PeopleModel>
}
