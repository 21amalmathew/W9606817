package uk.ac.tees.mad.W9606817

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import uk.ac.tees.mad.W9606817.Data.Local.Quote
import uk.ac.tees.mad.W9606817.Repository.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val db : FirebaseFirestore,
    private val repository: QuoteRepository
): ViewModel() {
    val currentUser = auth.currentUser
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    val isSignedIn = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> get() = _quotes
    init {
        viewModelScope.launch {
            delay(1000L)
            _loading.emit(false)
        }
        getandStore()
    }

    fun getandStore(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getQuotesAndStore()
            }catch (e:Exception){
                Log.d("MainViewModel", "Error fetching quotes", e)
            }
            loadQuotes()
        }
    }
    private fun loadQuotes() {

        viewModelScope.launch {
            _quotes.value = repository.getAllQuotes()
            Log.d("MainViewModel", _quotes.value.toString())
        }
    }


//    fun getQuotes() {
//        viewModelScope.launch {
//            try {
//                repository.loadQuotes(5)
//                Log.d("MainViewModel", "Quotes Loaded")
//                val quotes = repository.quotes
//                Log.d("MainViewModel", "Quotes: $quotes")
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Error fetching quotes", e)
//            }
//        }
//    }

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
            Toast.makeText(context,"Logged In successfully",Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                isLoading.value = false
                Toast.makeText(context,it.localizedMessage,Toast.LENGTH_SHORT).show()
            }
    }

}