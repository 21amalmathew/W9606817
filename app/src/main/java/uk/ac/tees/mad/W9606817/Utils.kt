package uk.ac.tees.mad.W9606817

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
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