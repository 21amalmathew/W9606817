package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel

@Composable
fun HomeScreen(vm: MainViewModel, navController: NavController) {
    Text(text = "Home Screen", fontSize = 100.sp)
}