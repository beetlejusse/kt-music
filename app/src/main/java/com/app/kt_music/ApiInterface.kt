package com.app.kt_music

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {

    @Headers("x-rapidapi-key: b98ea892b5msh56412be7a800345p1aa48cjsn174ac8a04df2", "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<DeezerData>
}