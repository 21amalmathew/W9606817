package uk.ac.tees.mad.W9606817.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp


@Composable
fun RegistrationScreen(vm: MainViewModel, navController: NavController) {
    val isLoading = vm.isLoading
    val loggedIn = vm.isSignedIn

    if (loggedIn.value){
        navController.navigate(NavigateInApp.TODAYSCREEN.route){
            popUpTo(0)
        }
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") })
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("password") })
            Button(onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    vm.signUp(context = context, email = email.value, password = password.value)
                    email.value = ""
                    password.value = ""
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                if (isLoading.value) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}