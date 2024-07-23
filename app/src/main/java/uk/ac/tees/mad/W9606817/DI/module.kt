package uk.ac.tees.mad.W9606817.DI

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDao
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDatabase
import uk.ac.tees.mad.W9606817.Data.Remote.QuotesAPI

@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quote_database"
        ).build()
    }

    @Provides
    fun provideQuoteDao(appDatabase: QuoteDatabase): QuoteDao {
        return appDatabase.quoteDao()
    }


    @Provides
    fun providesAuthentication() : FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirestore() : FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideQuotesAPI(): QuotesAPI = QuotesAPI.create()
}