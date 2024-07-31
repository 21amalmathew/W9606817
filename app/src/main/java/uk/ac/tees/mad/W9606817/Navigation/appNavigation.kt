package uk.ac.tees.mad.W9606817.Navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Screens.Archive
import uk.ac.tees.mad.W9606817.Screens.AuthenticationScreen
import uk.ac.tees.mad.W9606817.Screens.Detail
import uk.ac.tees.mad.W9606817.Screens.Favorite
import uk.ac.tees.mad.W9606817.Screens.LoginScreen
import uk.ac.tees.mad.W9606817.Screens.RegistrationScreen
import uk.ac.tees.mad.W9606817.Screens.SplashScreen
import uk.ac.tees.mad.W9606817.Screens.TodayScreen
import uk.ac.tees.mad.W9606817.Screens.YesterdayScreen

enum class NavigateInApp(val route:String){
    SPLASHSCREEN("splashscreen"),
    AUTHENTICATIONSCREEN("authenticationScreen"),
    LOGIN("login"),
    REGISTER("register"),
    TODAYSCREEN("todayScreen"),
    YESTERDAYSCREEN("yesterdayScreen"),
    DETAIL("detail/{quoteId}") ,
    FAVORITE("favorite"),
    ARCHIVE("archive"),
}

@Composable
fun appNavigation(callDark: () -> Unit){
    val navController = rememberNavController()
    val vm : MainViewModel = viewModel()
    Surface {
    NavHost(navController = navController, startDestination = NavigateInApp.SPLASHSCREEN.route ) {

            composable(NavigateInApp.SPLASHSCREEN.route) {
                SplashScreen(vm, navController)
            }
            composable(NavigateInApp.AUTHENTICATIONSCREEN.route) {
                AuthenticationScreen(vm, navController)
            }
            composable(NavigateInApp.REGISTER.route) {
                RegistrationScreen(vm, navController)
            }
            composable(NavigateInApp.LOGIN.route) {
                LoginScreen(vm, navController)
            }
            composable(NavigateInApp.TODAYSCREEN.route) {
                TodayScreen(vm, navController, callDark = callDark)
            }
            composable(NavigateInApp.YESTERDAYSCREEN.route) {
                YesterdayScreen(vm, navController)
            }
            composable(
                NavigateInApp.DETAIL.route,
                arguments = listOf(navArgument("quoteId") { type = NavType.StringType })
            ) { backStackEntry ->
                val quoteId = backStackEntry.arguments?.getString("quoteId") ?: ""
                Detail(vm, quoteId)
            }
            composable(NavigateInApp.FAVORITE.route) {
                Favorite(vm, navController)
            }
            composable(NavigateInApp.ARCHIVE.route) {
                Archive(vm, navController)
            }
        }
    }
}