package ctatum.animecatalog.repository

import ctatum.animecatalog.models.AnimeImage
import ctatum.animecatalog.models.Result

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import kotlin.collections.*

interface GetData {

    // Specify the request type and pass the relative URL
    // 'employees' matches the last qualifier on the URL

    // The full URL will be
    // BASE_URL = "https://api.jikan.moe/v3/search/"
    // plus
    // "anime?q={search value}"

    @GET("anime")
    fun getImageResults(@Query("q") searchArgParm: String): Call<AnimeImage>

}

