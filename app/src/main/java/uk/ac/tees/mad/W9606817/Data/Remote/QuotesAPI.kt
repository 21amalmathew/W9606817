package uk.ac.tees.mad.W9606817.Data.Remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {
    companion object {
        private const val BASE_URL = "https://api.quotable.io/"

        fun create(): QuotesAPI {
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(QuotesAPI::class.java)
        }
    }

    @GET("quotes/random")
    suspend fun getRandomQuotes(): List<QuoteResponse>
}

data class QuoteResponse(
    val _id: String,
    val content: String,
    val author: String,
    val date: String
)