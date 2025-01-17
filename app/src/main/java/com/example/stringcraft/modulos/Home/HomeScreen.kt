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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stringcraft.R

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Splash Screen",
                modifier = Modifier.height(50.dp),
            )

            Spacer(modifier =  Modifier.padding(6.dp))

            Text(
                text = "StringCraft",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 26.sp),
                color = Color.Black)
        }

        Spacer(modifier = Modifier.height(30.dp))

         Button(onClick = { /*TODO*/ },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .padding(14.dp)
                 .width(300.dp),
             enabled = true,
             contentPadding = PaddingValues(16.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color(red = 45, green = 23, blue= 47))
             ) {
             Text(text = "Calculate")
         }

         Button(onClick = { /*TODO*/ },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .padding(14.dp)
                 .width(300.dp),
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
    HomeScreen()
}