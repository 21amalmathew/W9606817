package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.MenuDialog
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp

@Composable
fun YesterdayScreen(vm: MainViewModel, navController: NavController) {
    val isMenuExpanded = remember { mutableStateOf(false) }
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
        if (isMenuExpanded.value){
            MenuDialog(navController, onDismiss = {isMenuExpanded.value = false})
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 0.dp)) {
                Text(
                    text = "Yesterday's Quotes",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f)
                )
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        isMenuExpanded.value = true
                    })
            }
            Column() {
            }
        }
    }
}