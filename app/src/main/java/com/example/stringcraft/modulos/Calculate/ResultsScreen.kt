package com.example.stringcraft.modulos.Calculate

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stringcraft.R
import com.example.stringcraft.modulos.navigation.ScreenRoot
import java.io.File
import java.io.FileOutputStream
import kotlin.text.*

@Composable
fun ResultsScreen(navController: NavController, scale: String, frets: String) {
    ResultTopAppBar(navController)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 12.dp, top = 52.dp, end = 12.dp)
        ) {
            Text(
                text = "Scale Length: $scale\nNumber of Frets: $frets",
                style = TextStyle(fontSize = 18.sp, color = Color.Transparent)
            )

            // Estado para controlar el loading
            val isLoading = remember { mutableStateOf(true) }
            // Estado para almacenar la información de los trastes
            val fretData = remember { mutableStateOf<List<FretsInfo>>(emptyList()) }

            // Simular el tiempo de carga de 3 segundos
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(3000)
                fretData.value = calculateInformationFromFrets(scale.toDouble(), frets.toInt())
                isLoading.value = false
            }
            Text(
                text = stringResource(R.string.title_calculation_results),
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 24.sp),
                color = Color(0XFF2D172F)
            )
            Spacer(modifier = Modifier.padding(14.dp))

            // Mostrar el indicador de carga o la tabla
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else {
                TableOfFrets(fretData.value)
            }

            Spacer(modifier = Modifier.padding(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BtnExport(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp), scale, frets
                )
            }
        }
    }
}

@Composable
fun TableOfFrets(value: List<FretsInfo>) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Cabecera de la tabla
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.column_name_one),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
                    color = Color(0XFF030303),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.column_name_two),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
                    color = Color(0XFF030303),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.column_name_three),
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
                    color = Color(0XFF030303),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Contenido de la tabla
            value.forEachIndexed { index, fret ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = fret.number.toString(),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = String.format("%.2f mm", fret.distanceFromNut),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = fret.distanceBetweenFrets?.let { String.format("%.2f mm", it) } ?: "N/A",
                        modifier = Modifier.weight(1f)
                    )
                }
                if (index < value.size -1){
                   Divider(
                       color = Color.Gray,
                       thickness = 1.dp,
                       modifier = Modifier.padding(horizontal = 3.dp)
                   )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
}

@Composable
fun BtnExport(modifier: Modifier, scale: String, frets: String){
    val context = LocalContext.current
    val data = calculateInformationFromFrets(scale.toDouble(), frets.toInt())

    Button(
        onClick = {
            exportFile(context, data)
        },
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
        Text(text = stringResource(R.string.btn_export), color = Color.White)
    }
}

fun calculateInformationFromFrets(scaleLongitude: Double, numberOfFrets: Int): List<FretsInfo>{
    val frets = mutableListOf<FretsInfo>()

    // Inicia desde la cejuela
    var previousDistance = scaleLongitude

    for(n in 1..numberOfFrets) {
       // Distancia entre trastes consecutivos
       val currentDistance = scaleLongitude / Math.pow(2.0, n / 12.0)

       // Distancia entre trastes consecutivos
       val distanceBetweenFrets = if (n > 1) previousDistance - currentDistance else null

       // Agregar la informacion del traste a la lista
       frets.add(FretsInfo(n, currentDistance, distanceBetweenFrets))

       // Actualizamos la distancia anterior
       previousDistance = currentDistance
    }
    return frets
}

fun exportFile(context: Context, data: List<FretsInfo>){
    try {
        val fileName = "Posiciones_trastes.txt"
        val file = File(context.getExternalFilesDir(null), fileName)
        FileOutputStream(file).use { output ->
            output.write("Trastes\tDistancia desde Cejuela (mm)\tDistancia entre Trastes (mm)\n".toByteArray())
            output.write("-".repeat(50).toByteArray())
            output.write("\n".toByteArray())

            data.forEach { fret ->
                val linea = String.format(
                    "",
                    fret.number,
                    fret.distanceFromNut,
                    fret.distanceBetweenFrets?.let { "".format(it) } ?: "N/A"
                )
                output.write(linea.toByteArray())
            }
        }
        Toast.makeText(context, "Archivo guardado: $fileName", Toast.LENGTH_LONG).show()
    }catch (e: Exception){
        Toast.makeText(context, "Error al guardar el archivo: ${e.message}", Toast.LENGTH_LONG).show()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultTopAppBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(R.string.topbar_title_results), style = TextStyle(fontSize = 18.sp)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0XFF2D172F)
        ),
        navigationIcon= {
            IconButton( onClick = {
                navController.navigate(ScreenRoot.CalculateScreen.route)
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        modifier = Modifier.shadow(
            elevation = 24.dp,
            spotColor = Color(0XFF2c2c2c)
        )
    )
}

@Preview
@Composable
fun ResultScreenPrev(){
    //ResultsScreen(navController = TODO(), scale = "", frets = "")
}