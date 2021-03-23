package ctatum.animecatalog.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null

    // Define the base URL - must end with a "/" or it crashes
    val BASE_URL = "https://api.jikan.moe/v3/search/"

    //*********************************************
    // Create the Retrofit instance
    //*********************************************

    // Add the converter
    // Build the Retrofit instance

    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!               // added '!!' - should never be null
        }
    //

}