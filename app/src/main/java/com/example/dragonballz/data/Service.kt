package com.example.dragonballz.data

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("api/characters?limit=100")
    fun getCharacters(): Call<CharacterResponse>
}
