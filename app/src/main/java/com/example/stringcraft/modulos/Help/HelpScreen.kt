package com.example.stringcraft.modulos.Help

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
fun HelpScreen(navController: NavController) {
        MyTopAppBar(navController)
        Spacer(modifier = Modifier.padding(12.dp))
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BodyHelp(Modifier.align(Alignment.Center))
        }
}

@Composable
fun BodyHelp(modifier: Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(14.dp))
        TitleScreen()
        Spacer(modifier = Modifier.padding(14.dp))
        ContentIllustration()
        Spacer(modifier = Modifier.padding(14.dp))
        AdditionalResources()
        Spacer(modifier = Modifier.padding(14.dp))
        AdMobBanner(adUnit = "ca-app-pub-3940256099942544/9214589741")
    }
}

@Composable
fun TitleScreen(){
    Spacer(modifier = Modifier.padding(12.dp))
    Text(
        text = stringResource(R.string.title_understanding),
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 24.sp),
        color = Color(0XFF2D172F)
    )
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = stringResource(R.string.description_app),
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = Color(0XFF342e23)
    )
}

@Composable
fun ContentIllustration(){
    Text(
        text = stringResource(R.string.title_illustration),
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 20.sp),
        color = Color(0XFF2D172F)
    )
    Spacer(modifier = Modifier.padding(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Explication(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
        ExplicationTwo(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
    }
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = stringResource(R.string.desciption_images),
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = Color(0XFF342e23)
    )
}

@Composable
fun Explication(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.cejuelas),
        contentDescription = "Splash Screen",
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ExplicationTwo(modifier: Modifier){
    Image(
        painter = painterResource(id = R.drawable.puente),
        contentDescription = "Splash Screen",
        modifier = modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AdditionalResources(){
    Text(
        text = stringResource(R.string.title_additional),
        fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 20.sp),
        color = Color(0XFF2D172F)
    )
    Spacer(modifier = Modifier.padding(8.dp))
    HyperlinkText(link = "https://www.google.com")
}

@Composable
fun HyperlinkText(link: String) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        pushStringAnnotation(tag = "URL", annotation = link)
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(R.string.link_manual))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                    context.startActivity(intent)
                }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(R.string.topbar_title_help), style = TextStyle(fontSize = 18.sp)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0XFF2D172F)
        ),
        navigationIcon= {
            IconButton( onClick = {
               navController.navigate(ScreenRoot.HomeScreen.route)
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }},
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
fun HelpScreenPrev(){
    //HelpScreen(navController = TODO())
}