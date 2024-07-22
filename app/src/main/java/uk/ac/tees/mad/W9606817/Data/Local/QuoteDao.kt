package uk.ac.tees.mad.W9606817.Data.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {
    @Query ("SELECT * FROM quotes WHERE date =:date")
    suspend fun getQuoteByDate(date:String):Quote

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuote(quote:Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

}