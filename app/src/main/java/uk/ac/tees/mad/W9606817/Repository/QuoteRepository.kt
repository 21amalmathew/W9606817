package uk.ac.tees.mad.W9606817.Repository

import androidx.lifecycle.LiveData
import uk.ac.tees.mad.W9606817.Data.Local.Quote
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDao
import uk.ac.tees.mad.W9606817.Data.Remote.QuotesAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val quotesAPI: QuotesAPI
) {
    val quotes: LiveData<List<Quote>> = quoteDao.getallQuote()

    suspend fun loadQuotes() {
        val response = quotesAPI.getRandomQuotes()
        val quoteEntities = response.map {
            Quote(
                id = it._id,
                content = it.content,
                author = it.author,
                date = it.date
            )
        }
        quoteDao.insertQuote(quoteEntities)
    }
}
//    suspend fun getDailyQuotes(date: String): Quote {
//        var quote = quoteDao.getQuoteByDate(date)
//        if (quote == null) {
//            val response = quotesAPI.getRandomQuotes(30)
//            if (response.isSuccessful){
//                val quoteResponse = response.body()
//                quote = Quote(
//                    id = quoteResponse
//                )
//            }
//        }
//    }
