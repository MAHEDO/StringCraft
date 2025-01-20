package com.example.stringcraft.modulos.splash.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stringcraft.R
import com.example.stringcraft.modulos.navigation.ScreenRoot

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Splash Screen",
                modifier = Modifier.height(40.dp),
            )

            Spacer(modifier =  Modifier.padding(6.dp))

            Text(
                text = "StringCraft",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 26.sp),
                color = Color(0XFF2D172F)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            navController.navigate(ScreenRoot.CalculateScreen.route)
         },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(14.dp),
             enabled = true,
             contentPadding = PaddingValues(16.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color(red = 45, green = 23, blue= 47))
             ) {
             Text(text = "Calculate")
         }

         Button(onClick = { /*TODO*/ },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(14.dp),
             enabled = true,
             border = BorderStroke(width = 1.dp, color = Color.Black),
             contentPadding = PaddingValues(16.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color.White)
             ) {
            Text(text = "Help/Information", color = Color.Black)
         }
    }
}

@Preview
@Composable
fun HomeScreenPre(){
    HomeScreen(
        navController = TODO()
    )
}