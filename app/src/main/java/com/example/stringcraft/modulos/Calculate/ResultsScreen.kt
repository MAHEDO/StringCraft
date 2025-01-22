package com.example.stringcraft.modulos.Calculate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stringcraft.modulos.Help.BodyHelp

@Composable
fun ResultsScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ResultHelp(Modifier.align(Alignment.Center))
    }
}

@Composable
fun ResultHelp(modifier: Modifier) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(14.dp))
        Text(
            text = "Calculation Results",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 28.sp),
            color = Color(0XFF2D172F)
        )
        Spacer(modifier = Modifier.padding(14.dp))

        Spacer(modifier = Modifier.padding(14.dp))
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            BtnExport(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            BtnBack(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun BtnExport(modifier: Modifier){
    Button(
        onClick = { /*TODO*/},
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        enabled = true,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(
                red = 45,
                green = 23,
                blue = 47)
        )
    ) {
        Text(text = "Export", color = Color.White)
    }
}

@Composable
fun BtnBack(modifier: Modifier){
    Button(
        onClick = { /*TODO*/},
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        enabled = true,
        contentPadding = PaddingValues(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(
                red = 45,
                green = 23,
                blue = 47)
        )
    ) {
        Text(text = "Back", color = Color.White)
    }
}

@Preview
@Composable
fun ResultScreenPrev(){
    ResultsScreen()
}