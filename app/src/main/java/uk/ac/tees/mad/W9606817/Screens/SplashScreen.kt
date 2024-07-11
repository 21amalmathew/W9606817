package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import uk.ac.tees.mad.W9606817.MainViewModel
import uk.ac.tees.mad.W9606817.R

@Composable
fun SplashScreen(vm: MainViewModel,navigateToNextScreen: () -> Unit){
    val loadingState = vm.loading.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Display a splash screen design while loading
        if (loadingState.value) {
            SplashDesign()
        } else {
            // Optionally, show a loading indicator or transition effect
        }
    }
}

@Composable
fun SplashDesign(){
    Box {
        Image(painter = painterResource(id = R.drawable.quote_app),modifier = Modifier.size(500.dp), contentDescription = "SplashImage")
    }
}

@Preview
@Composable
fun show(){
    SplashDesign()
}