package uk.ac.tees.mad.W9606817.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthenticationScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.align(Alignment.Center)) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "sign-up")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "login")
            }
        }
    }
}