package uk.ac.tees.mad.W9606817

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.W9606817.Navigation.NavigateInApp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun CommonProgressBar() {
        CircularProgressIndicator()
}
fun checkSignedIn(vm: MainViewModel, navController: NavController) {
        val currentUser = vm.currentUser

        if (currentUser != null) {
                navController.navigate(NavigateInApp.TODAYSCREEN.route){
                        popUpTo(0)
                }
        }
}

fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
}

fun getTodayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDialog(navController: NavController,onDismiss : () -> Unit){
        AlertDialog(onDismissRequest = { onDismiss.invoke() },
                modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                        ){
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(modifier = Modifier.clickable {
                                navController.navigate(NavigateInApp.FAVORITE.route)
                        }) {
                                Text(text = "Favorites", fontSize = 20.sp)
                                Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(modifier = Modifier.clickable {
                                navController.navigate(NavigateInApp.SETTING.route)
                        }) {
                                Text(text = "Settings",fontSize = 20.sp)
                                Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(modifier = Modifier.clickable {
                                navController.navigate(NavigateInApp.ARCHIVE.route)
                        }) {
                                Text(text = "Settings",fontSize = 20.sp)
                                Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
                        }
                }
        }
}

@Composable
fun QuoteView(content: String, author : String, date : String, onFavClick : () -> Unit){
        val colors = listOf(Color(0xFF52ACFF), Color(0xFFFFE32C)) // Define your gradient colors
        val brush = Brush.horizontalGradient(colors)
        Card(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))) {
                Column(modifier = Modifier
                        .background(brush = brush)
                        .padding(8.dp)) {
                        Text(text = content, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Row {
                                Text(text = "~ ${author}", modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp))
                                Text(text = "-${date}", modifier = Modifier.padding(4.dp))
                                Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null, tint = Color.Red,
                                        modifier = Modifier.padding(start = 4.dp).clickable {
                                                onFavClick()
                                        })
                        }
                }
        }
}

