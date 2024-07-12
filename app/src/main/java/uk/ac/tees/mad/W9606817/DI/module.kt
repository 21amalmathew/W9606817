package uk.ac.tees.mad.W9606817.DI

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class module {

    @Provides
    fun providesAuthentication() : FirebaseAuth = Firebase.auth
}