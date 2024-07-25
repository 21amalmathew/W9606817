package uk.ac.tees.mad.W9606817.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Screens.Archive
import uk.ac.tees.mad.W9606817.Screens.AuthenticationScreen
import uk.ac.tees.mad.W9606817.Screens.Favorite
import uk.ac.tees.mad.W9606817.Screens.LoginScreen
import uk.ac.tees.mad.W9606817.Screens.RegistrationScreen
import uk.ac.tees.mad.W9606817.Screens.Setting
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
    SETTING("setting"),
    FAVORITE("favorite"),
    ARCHIVE("archive")
}


@Composable
fun appNavigation(){
    val navController = rememberNavController()
    val vm : MainViewModel = viewModel()
    NavHost(navController = navController, startDestination = NavigateInApp.SPLASHSCREEN.route ){
        composable(NavigateInApp.SPLASHSCREEN.route){
            SplashScreen(vm,navController)
        }
        composable(NavigateInApp.AUTHENTICATIONSCREEN.route){
            AuthenticationScreen(vm,navController)
        }
        composable(NavigateInApp.REGISTER.route){
            RegistrationScreen(vm,navController)
        }
        composable(NavigateInApp.LOGIN.route){
            LoginScreen(vm,navController)
        }
        composable(NavigateInApp.TODAYSCREEN.route){
            TodayScreen(vm,navController)
        }
        composable(NavigateInApp.YESTERDAYSCREEN.route){
            YesterdayScreen(vm,navController)
        }
        composable(NavigateInApp.SETTING.route){
            Setting(vm,navController)
        }
        composable(NavigateInApp.FAVORITE.route){
            Favorite(vm,navController)
        }
        composable(NavigateInApp.ARCHIVE.route){
            Archive(vm,navController)
        }
    }
}