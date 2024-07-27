package uk.ac.tees.mad.W9606817.Screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp
import uk.ac.tees.mad.W9606817.QuoteView
import uk.ac.tees.mad.W9606817.SortDialog

@Composable
fun Favorite(vm: MainViewModel, navController: NavController) {
    val favoriteList = vm.favorites.observeAsState(initial = emptyList())
    val searchBox = remember { mutableStateOf("") }
    val filterBox = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        if (filterBox.value){
            SortDialog(onDismiss = { filterBox.value = false }, onAscendingClick = { vm.sortByFavASC() }, onDescendingClick = {vm.sortByFavDESC()})
        }
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
            Icon(imageVector = Icons.Rounded.Edit, contentDescription = null, modifier = Modifier
                .size(30.dp)
                .clickable { filterBox.value = true })
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)) {
            OutlinedTextField(value = searchBox.value, onValueChange = { searchBox.value = it },
                label = {
                    Text(text = "Search")
                },modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null, modifier = Modifier
                .size(70.dp)
                .clickable { vm.searchFavorites(searchBox.value) })
        }
            LazyColumn {
            items(favoriteList.value) { item ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    FavQuoteView(
                        content = item.q,
                        author = item.a,
                        date = item.deviceDate
                    ) { }
                }
            }
        }
    }
}

@Composable
fun FavQuoteView(content: String, author : String, date : String, onQuoteClick : () -> Unit){
    val colors = listOf(Color(0xFF52ACFF), Color(0xFFFFE32C)) // Define your gradient colors
    val brush = Brush.horizontalGradient(colors)
    Card(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .clickable { }) {
        Column(modifier = Modifier
            .background(brush = brush)
            .padding(8.dp)) {
            Text(text = content, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row {
                Text(text = "~ ${author}", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp))
                Text(text = "-${date}", modifier = Modifier.padding(4.dp))
            }
        }
    }
}
