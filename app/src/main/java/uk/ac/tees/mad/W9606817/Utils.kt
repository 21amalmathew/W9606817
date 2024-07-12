package uk.ac.tees.mad.W9606817

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp

@Composable
fun CommonProgressBar() {
        CircularProgressIndicator()
}
fun checkSignedIn(vm: MainViewModel, navController: NavController) {
        val currentUser = vm.currentUser

        if (currentUser != null) {
                navController.navigate(NavigateInApp.HOME.route)
        }
}