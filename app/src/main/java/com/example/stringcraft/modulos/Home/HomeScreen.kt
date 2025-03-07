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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        LogoTwo()
        Spacer(modifier =  Modifier.padding(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.guitar),
                contentDescription = "Splash Screen",
                modifier = Modifier.height(30.dp),
            )

            Text(
                text = stringResource(R.string.slogan_name_app),
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 24.sp),
                color = Color(0XFF2D172F)
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Button(onClick = {
            navController.navigate(ScreenRoot.CalculateScreen.route)
         },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(14.dp),
             enabled = true,
             contentPadding = PaddingValues(16.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF2D172F))
             ) {
             Text(text = stringResource(R.string.btn_calculate))
         }

         Button(onClick = {
             navController.navigate(ScreenRoot.HelpScreen.route)
         },
             shape = RoundedCornerShape(12.dp),
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(14.dp),
             enabled = true,
             border = BorderStroke(width = 1.dp, color = Color.Black),
             contentPadding = PaddingValues(16.dp),
             colors = ButtonDefaults.buttonColors(containerColor = Color.White)
             ) {
            Text(text = stringResource(R.string.btn_text_helpinformation), color = Color.Black)
         }

        Button(onClick = {
            navController.navigate(ScreenRoot.AboutScreen.route)
        },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            enabled = true,
            border = BorderStroke(width = 1.dp, color = Color.Black),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(text = stringResource(R.string.btn_about), color = Color.Black)
        }

        Spacer(modifier = Modifier.height(20.dp))

        //version name
        Text(
            text = "V1.0.1",
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(fontSize = 14.sp),
            color = Color(0XFF2D172F)
        )
    }
}

@Composable
fun LogoTwo(){
    Card(
        modifier = Modifier.size(200.dp),
        //radius
        shape = RoundedCornerShape(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun HomeScreenPre(){
    //HomeScreen(navController = TODO())
}