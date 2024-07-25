package uk.ac.tees.mad.W9606817.Repository

import android.util.Log
import uk.ac.tees.mad.W9606817.Data.Favorites.FavoriteDao
import uk.ac.tees.mad.W9606817.Data.Favorites.FavoriteQuotes
import uk.ac.tees.mad.W9606817.Data.Local.Quote
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDao
import uk.ac.tees.mad.W9606817.Data.Remote.QuoteService
import uk.ac.tees.mad.W9606817.getTodayDate
import uk.ac.tees.mad.W9606817.getYesterdayDate
import java.time.LocalDate
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteDao: QuoteDao,
    private val favoriteDao: FavoriteDao,
    private val quotesAPI: QuoteService
) {
    suspend fun getQuotesAndStore() {
        val response = quotesAPI.getQuote(10)
        if (response.isSuccessful) {
            val currentDate = LocalDate.now()
            Log.d("currentDate", currentDate.toString())
            response.body()?.let { quotes ->
                quoteDao.insertQuote(quotes.map {
                    Quote(_id = it._id,
                        author = it.author,
                        content = it.content,
                        authorSlug = it.authorSlug,
                        dateAdded = it.dateAdded,
                        dateModified = it.dateModified,
                        length = it.length,
                        deviceDate = currentDate.toString())
                })
                Log.d("QuoteResponse", quotes.toString())
            }
        } else {
            Log.e("QuoteResponse", "Failed to fetch quotes")
        }
    }

    suspend fun getAllQuotes(): List<Quote> {
        return quoteDao.getallQuote()
    }

    fun getQuotesFromToday(): List<Quote> {
        val todayDate = getTodayDate()
        Log.d("todayDate",todayDate)
        return quoteDao.getQuotesFromToday(todayDate)
    }

    fun getQuotesFromYesterday(): List<Quote> {
        val yesterdayDate = getYesterdayDate()
        Log.d("Yesterday", yesterdayDate)
        return quoteDao.getQuotesFromYesterday(yesterdayDate)
    }

    suspend fun addFavorites(favorite: FavoriteQuotes){
        favoriteDao.insertFavorite(favorite)
    }
    fun getFavorites(): List<FavoriteQuotes> {
        return favoriteDao.getAllFavorites()
    }
}

