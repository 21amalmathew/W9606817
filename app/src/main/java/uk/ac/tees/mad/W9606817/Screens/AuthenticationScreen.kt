package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp

@Composable
fun AuthenticationScreen(vm: MainViewModel, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { navController.navigate(NavigateInApp.REGISTER.route) }) {
                Text(text = "sign up")
            }
            Button(onClick = { navController.navigate(NavigateInApp.LOGIN.route) }) {
                Text(text = "log in")
            }
        }
    }
}