package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.QuoteView

@Composable
fun Favorite(vm: MainViewModel, navController: NavController) {
    vm.loadFavorites()
    val favoriteList = vm.favorites.observeAsState(initial = emptyList())
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 0.dp)
        ) {
            Text(
                text = "Favorite Quotes",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(1f)
            )
        }
        LazyColumn {
            items(favoriteList.value) { item ->
                Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    QuoteView(
                        content = item.content,
                        author = item.author,
                        date = item.deviceDate
                    ) {

                    }
                }
            }
        }
    }
}