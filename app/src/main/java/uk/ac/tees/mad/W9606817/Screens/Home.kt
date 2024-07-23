package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel

@Composable
fun HomeScreen(vm: MainViewModel, navController: NavController) {

    val quotes = vm.quotesFromToday.observeAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(quotes.value){ item->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = item.content)
                    Text(text = item.deviceDate)
                }
            }
        }
    }
}