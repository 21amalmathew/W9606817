package uk.ac.tees.mad.W9606817

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val auth : FirebaseAuth
): ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    init {
        viewModelScope.launch {
            delay(1000L)
            _loading.emit(false)
        }
    }
    fun signUp(context: Context, email:String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            Toast.makeText(context,"Sign Up Successfull",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e->
            Log.d("TAG","Signup failed ${e.localizedMessage}")
            Toast.makeText(context,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}