package uk.ac.tees.mad.W9606817.Data.Remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {
    @GET("quotes/random")
    suspend fun getQuote(@Query("limit") limit : Int): Response<List<QuoteItem>>
}
