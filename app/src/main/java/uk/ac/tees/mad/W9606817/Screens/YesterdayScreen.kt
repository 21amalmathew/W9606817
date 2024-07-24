package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp

@Composable
fun YesterdayScreen(vm: MainViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, delta ->
                    if (delta < 0) {
                        navController.navigate(NavigateInApp.TODAYSCREEN.route) {
                            popUpTo(0)
                        }
                    }
                }
            }
//            .background(Color.Blue)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Yesterday Quotes", fontSize = 30.sp, modifier = Modifier.padding(top = 30.dp, start = 10.dp))
            }
        }
    }
}