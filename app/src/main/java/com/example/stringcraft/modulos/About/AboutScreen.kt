package com.example.stringcraft.modulos.About

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.stringcraft.R
import com.example.stringcraft.modulos.navigation.ScreenRoot
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AboutScreen(navController: NavController) {
    AboutTopAppBar(navController)
    Box(Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        BodyAbout(Modifier.align(Alignment.Center))
    }
}

@Composable
fun BodyAbout(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 42.dp),
        verticalArrangement = Arrangement.Top
    ) {
       ProjectDescription()
       HistoryDescription()
       ImageBackground()
       DevelopDescription()
       AdMobBanner(adUnit = "ca-app-pub-3940256099942544/9214589741")
    }
}

@Composable
fun ProjectDescription(){
    Spacer(modifier = Modifier.padding(14.dp))
    Text(
        text = stringResource(R.string.title_project_description),
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 20.sp),
        color = Color(0XFF2D172F)
    )
    Spacer(modifier = Modifier.padding(14.dp))
    Text(
        text = stringResource(R.string.project_description),
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = Color(0XFF342e23)
    )
}

@Composable
fun HistoryDescription(){
    Spacer(modifier = Modifier.padding(14.dp))
    Text(
        text = stringResource(R.string.title_history_or_motivation),
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 20.sp),
        color = Color(0XFF2D172F)
    )
    Spacer(modifier = Modifier.padding(14.dp))
    Text(
        text = stringResource(R.string.history_description),
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = Color(0XFF342e23)
    )
}

@Composable
fun ImageBackground(){
    Spacer(modifier = Modifier.padding(14.dp))
    Image(
        painter = painterResource(id = R.drawable.luthier),
        contentDescription = "Luthier Screen",
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
        contentScale = ContentScale.FillWidth

    )
}

@Composable
fun DevelopDescription(){
    Spacer(modifier = Modifier.padding(14.dp))
    Text(
        text = stringResource(R.string.develop_description),
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = Color(0XFF342e23)
    )
    Spacer(modifier = Modifier.padding(14.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTopAppBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(R.string.topbar_title_about), style = TextStyle(fontSize = 18.sp)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0XFF2D172F),
            titleContentColor = Color.White
        ),
        navigationIcon= {
            IconButton( onClick = {
                navController.navigate(ScreenRoot.HomeScreen.route)
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0XFF2D172F)
            )) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(R.color.white)
                )
            }
        },
        modifier = Modifier.shadow(
            elevation = 24.dp,
            spotColor = Color(0XFF2c2c2c)
        )
    )
}

@Composable
fun AdMobBanner(adUnit: String){
   AndroidView(
       modifier = Modifier
           .fillMaxWidth()
           .height(80.dp),
       factory = { context ->
           AdView(context).apply {
               setAdSize(AdSize.BANNER)
               adUnitId = adUnit
               loadAd(AdRequest.Builder().build())
           }
       }
   )
}

@Preview
@Composable
fun AboutScreenPrev(){
    AboutScreen( navController = TODO())
}