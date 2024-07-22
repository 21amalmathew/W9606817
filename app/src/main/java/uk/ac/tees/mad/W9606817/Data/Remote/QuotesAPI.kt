package uk.ac.tees.mad.W9606817.Data.Remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {
    @GET("quotes/random")
    fun getRandomQuotes(@Query("limit") limit: Int): Call<List<QuoteResponse>>
}

data class QuoteResponse(
    val _id: String,
    val content: String,
    val author: String,
)