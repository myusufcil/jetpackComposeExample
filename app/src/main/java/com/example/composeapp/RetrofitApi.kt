package com.example.composeapp

import com.example.composeapp.constant.Constants.API_KEY
import com.example.composeapp.response.Recommendations
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApi {
    @GET("songs/list-recommendations?key=484129036&locale=en-US")
    suspend fun getUserData(
        @Header("x-rapidapi-host") rapidHost: String="shazam.p.rapidapi.com",
        @Header("x-rapidapi-key") token: String = API_KEY
    ): Recommendations
}