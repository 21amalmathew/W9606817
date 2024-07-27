package uk.ac.tees.mad.W9606817.Data.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {
//    @Query ("SELECT * FROM quotes WHERE date =:date")
//    suspend fun getQuoteByDate(date:String):Quote

    @Query("SELECT * FROM quotes WHERE deviceDate = :today ORDER BY deviceDate ASC")
    fun getQuotesFromToday(today: String): List<Quote>

    @Query("SELECT * FROM quotes WHERE deviceDate = :yesterday ORDER BY deviceDate ASC")
    fun getQuotesFromYesterday(yesterday: String): List<Quote>

    @Query ("SELECT * FROM quotes")
    suspend fun getallQuote(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote:List<Quote>)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("Select * from quotes WHERE q  LIKE '%' || :content || '%'")
    suspend fun searchQuotes(content: String) : List<Quote>

    @Query("Select * from quotes ORDER BY q ASC")
    suspend fun sortByASC() : List<Quote>

    @Query("Select * from quotes ORDER BY q DESC")
    suspend fun sortByDesc() : List<Quote>
}