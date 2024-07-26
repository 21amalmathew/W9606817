package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.W9606817.MainViewModel

@Composable
fun Detail (vm: MainViewModel, quoteId: String) {
    val quotes = vm.quotesFromToday.observeAsState(initial = emptyList())
    val quote = quotes.value.find { it._id == quoteId }
    val colors = listOf(Color(0xFF52ACFF), Color(0xFFFFE32C)) // Define your gradient colors
    val brush = Brush.horizontalGradient(colors)
    quote?.let {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(brush = brush)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = it.content, fontSize = 20.sp)
                Text(text = "Author: ${it.author}", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
                Text(text = "Date: ${it.deviceDate}", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
                Text(text = "Date: ${it.authorSlug}", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
                Text(text = "Date: ${it.dateModified}", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            }
        }
    } ?: run {
        Text(text = "Quote not found", modifier = Modifier.padding(16.dp))
    }
}