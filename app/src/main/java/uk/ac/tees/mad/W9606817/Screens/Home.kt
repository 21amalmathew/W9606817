package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp

@Composable
fun TodayScreen(vm: MainViewModel, navController: NavController) {

    val quotes = vm.quotesFromToday.observeAsState(initial = emptyList())
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectHorizontalDragGestures { _, delta ->
                if (delta > 0) {
                    navController.navigate(NavigateInApp.YESTERDAYSCREEN.route)
                }
            }
        }
//        .background(Color.Green)
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Today's Quotes",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 30.dp, start = 0.dp)
            )
            Column {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(quotes.value) { item ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = item.content)
                            Text(text = item.deviceDate)
                        }
                    }
                }
            }
        }
    }
}

