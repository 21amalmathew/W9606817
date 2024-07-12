package uk.ac.tees.mad.W9606817.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Screens.AuthenticationScreen
import uk.ac.tees.mad.W9606817.Screens.HomeScreen
import uk.ac.tees.mad.W9606817.Screens.LoginScreen
import uk.ac.tees.mad.W9606817.Screens.RegistrationScreen
import uk.ac.tees.mad.W9606817.Screens.SplashScreen

enum class NavigateInApp(val route:String){
    SPLASHSCREEN("splashscreen"),
    AUTHENTICATIONSCREEN("authenticationScreen"),
    LOGIN("login"),
    REGISTER("register"),
    HOME("home")
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
        composable(NavigateInApp.HOME.route){
            HomeScreen(vm,navController)
        }
    }
}