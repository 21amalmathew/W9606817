package uk.ac.tees.mad.W9606817

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.W9606817.Data.Favorites.FavoriteQuotes
import uk.ac.tees.mad.W9606817.Data.Local.Quote
import uk.ac.tees.mad.W9606817.Repository.QuoteRepository
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val repository: QuoteRepository,
) : ViewModel() {
    val currentUser = auth.currentUser
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    val isSignedIn = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> get() = _quotes

    private val _quotesFromYesterday = MutableLiveData<List<Quote>>()
    val quotesFromYesterday: LiveData<List<Quote>> get() = _quotesFromYesterday

    private val _quotesFromToday = MutableLiveData<List<Quote>>()
    val quotesFromToday: LiveData<List<Quote>> get() = _quotesFromToday

    private val _favorites = MutableLiveData<List<FavoriteQuotes>>()
    val favorites: LiveData<List<FavoriteQuotes>> get() = _favorites

    init {
        viewModelScope.launch {
            delay(1000L)
            _loading.emit(false)
        }
        getandStore()
    }

    fun getandStore() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                repository.getQuotesAndStore()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Error fetching quotes", e)
            }
            quotesFromTodays()
            quotesFromYesterday()
            loadAllQuotes()
        }
    }

    fun quotesFromTodays() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotesfromtoday = repository.getQuotesFromToday()
            _quotesFromToday.postValue(quotesfromtoday)
            Log.d("Todays Quote", quotesfromtoday.toString())
        }
    }

    fun quotesFromYesterday() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotesFromYesterday = repository.getQuotesFromYesterday()
            _quotesFromYesterday.postValue(quotesFromYesterday)
            Log.d("Yesterday Quote", quotesFromYesterday.toString())
        }
    }

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repository.getFavorites()
            _favorites.postValue(favorites)
        }
    }
    private fun loadAllQuotes() {

        viewModelScope.launch {
            _quotes.value = repository.getAllQuotes()
            Log.d("MainViewModel", _quotes.value.toString())
            Log.d("Todays Quote", _quotesFromToday.value.toString())
        }
    }

    fun addFavorites(favQuote: Quote) {
        val result = mapEntity(favQuote)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorites(result)
        }

    }

    fun mapEntity(quote: Quote): FavoriteQuotes {
        return FavoriteQuotes(
            _id = quote._id,
            content = quote.content,
            author = quote.author,
            authorSlug = quote.authorSlug,
            dateAdded = quote.dateAdded,
            dateModified = quote.dateModified,
            length = quote.length,
            deviceDate = quote.deviceDate
        )
    }

    fun signUp(context: Context, email: String, password: String) {
        isLoading.value = true
        db.collection("users").whereEqualTo("email", email).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("User", "Email does not exist")
                    // Email does not exist, invoke success callback
                    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                        val userData = hashMapOf("email" to email)
                        db.collection("User").document(email).set(userData)
                        Toast.makeText(context, "Sign Up Successfull", Toast.LENGTH_SHORT).show()
                        isSignedIn.value = true
                        isLoading.value = false
                    }.addOnFailureListener { e ->
                        Log.d("TAG", "Signup failed ${e.localizedMessage}")
                        Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()
                        isLoading.value = false
                    }
                } else {
                    // Email already exists
                    isLoading.value = false
                    Toast.makeText(context, "User Already Exist", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener { e ->
                isLoading.value = false
                Log.w("TAG", "Error checking email existence", e)
                // Handle failure scenario, e.g., show error message to user

            }

    }

    fun logIn(context: Context, email: String, password: String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            isSignedIn.value = true
            isLoading.value = false
            Toast.makeText(context, "Logged In successfully", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                isLoading.value = false
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }

}