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

    @Query ("SELECT * FROM quotes")
    suspend fun getallQuote(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote:List<Quote>)

    @Update
    suspend fun updateQuote(quote: Quote)

}