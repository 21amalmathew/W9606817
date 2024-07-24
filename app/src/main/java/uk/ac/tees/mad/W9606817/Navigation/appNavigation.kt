package uk.ac.tees.mad.W9606817.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.Screens.AuthenticationScreen
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
    ARCHIEVE("archieve")
}


@Composable
fun appNavigation(){
    val navController = rememberNavController()
    val vm : MainViewModel = viewModel()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Green
            ){
                IconButton(onClick = {
                selected.value = Icons.Default.Home
                    navController.navigate(NavigateInApp.TODAYSCREEN.route){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if(selected.value == Icons.Default.Home) Color.White else Color.DarkGray)
                }

                IconButton(onClick = {
                    selected.value = Icons.Default.ArrowBack
                    navController.navigate(NavigateInApp.YESTERDAYSCREEN.route){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if(selected.value == Icons.Default.ArrowBack) Color.White else Color.DarkGray)
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Settings
                    navController.navigate(NavigateInApp.SETTING.route){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if(selected.value == Icons.Default.Settings) Color.White else Color.DarkGray)
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Favorite
                    navController.navigate(NavigateInApp.FAVORITE.route){
                        popUpTo(0)
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(30.dp),
                        tint = if(selected.value == Icons.Default.Favorite) Color.White else Color.DarkGray)
                }
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp), contentAlignment = Alignment.BottomEnd){
                    FloatingActionButton(onClick = { navController.navigate(NavigateInApp.ARCHIEVE.route)}) {
                        Icon(imageVector = Icons.Rounded.Star, contentDescription = null, tint = Color.Green)
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = NavigateInApp.SPLASHSCREEN.route, modifier = Modifier.padding(paddingValues) ){
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
        }
    }
}

@Preview
@Composable
fun appNavigationPreview(){
    appNavigation()

}