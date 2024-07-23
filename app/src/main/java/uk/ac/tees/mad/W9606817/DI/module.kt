package uk.ac.tees.mad.W9606817.DI

import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDao
import uk.ac.tees.mad.W9606817.Data.Local.QuoteDatabase
import uk.ac.tees.mad.W9606817.Data.Remote.QuoteService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): QuoteDatabase {
        return QuoteDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuoteDao(database: QuoteDatabase): QuoteDao {
        return database.quoteDao()
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    @Provides
    fun providesQuoteService(retrofit: Retrofit): QuoteService {
        return retrofit.create(QuoteService::class.java)
    }

    @Provides
    fun providesAuthentication() : FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirestore() : FirebaseFirestore = Firebase.firestore

}