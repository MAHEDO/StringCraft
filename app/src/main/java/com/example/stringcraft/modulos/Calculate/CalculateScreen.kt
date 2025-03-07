package com.example.stringcraft.modulos.Calculate

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.stringcraft.R
import com.example.stringcraft.modulos.MinimalDialog
import com.example.stringcraft.modulos.navigation.ScreenRoot

@Composable
fun CalculateScreen(navController: NavController) {
    CalTopAppBar(navController)
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Body(Modifier.align(Alignment.Center), navController)
    }
}

@Composable
fun Body(modifier: Modifier, navController: NavController) {
    var scale by remember { mutableStateOf("") }
    var selectedFrets by remember { mutableStateOf("18") } // valor por defecto

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 42.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(14.dp))
        Text(
            text = stringResource(R.string.title_instrument_parameters),
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 24.sp),
            color = Color(0XFF2D172F)
        )
        Spacer(modifier = Modifier.padding(14.dp))
        Text(
            text = stringResource(R.string.title_scale_length),
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 14.sp),
            color = Color(0xFF030303)
        )
        Spacer(modifier = Modifier.padding(6.dp))
        ScaleText(scale) { scale = it }
        Spacer(modifier = Modifier.padding(18.dp))
        Text(
            text = stringResource(R.string.title_number_frets),
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 14.sp),
            color = Color(0XFF030303)
        )
        Spacer(modifier = Modifier.padding(6.dp))
        NumberFretsMenu{ selectedFrets = it }
        Spacer(modifier = Modifier.padding(18.dp))
        //SwitchViewOptions()
        /*Text(
            text = "Predefined Instruments Options",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 14.sp),
            color = Color(0XFF030303)
        )
        ExclusiveCheckboxList()*/
        Spacer(modifier = Modifier.padding(18.dp))
        BtnCalculate(navController, scale, selectedFrets)
    }
}

@Composable
fun ScaleText(scale: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = scale,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(R.string.placeholder_enter_scale)) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF8A8A8A),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberFretsMenu(onFretsSelected: (String) -> Unit) {
    val options = listOf("12", "16", "18", "19", "20", "22", "24")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("18") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedOptionText,
            onValueChange = { },
            placeholder = { Text("18") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            readOnly = true,
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .exposedDropdownSize(true)
        ) {
            options.forEach { selectedOption ->
                DropdownMenuItem(
                    text = { Text(selectedOption) },
                    onClick = {
                        selectedOptionText = selectedOption
                        onFretsSelected(selectedOption)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun getOptions(titles: List<String>, selectedTitle: String?, onSelectionChange:(String) -> Unit): List<CheckInfo> {
    return titles.map { title ->
        CheckInfo(
            title = title,
            selected = (title == selectedTitle),
            onCheckedChange = { isChecked ->
                if(isChecked){
                    onSelectionChange(title)
                }
            }
        )
    }
}

@Composable
fun SwitchViewOptions() {
    var state by remember { mutableStateOf(false) }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = false
    )
}

@Composable
fun ExclusiveCheckboxList() {
    val options = listOf(stringResource(R.string.option_check_first), stringResource(R.string.option_check_two),
        stringResource(
            R.string.option_check_three
        )
    )
    var selectedTitle by rememberSaveable { mutableStateOf<String?>(null) }

    val checkInfoList = getOptions(
        titles = options,
        selectedTitle = selectedTitle,
        onSelectionChange = { newTitle -> selectedTitle = newTitle }
    )

    Column {
        checkInfoList.forEach { checkInfo ->
            CheckBoxCombo(checkInfo)
        }
    }
}

@Composable
fun CheckBoxCombo(checkInfo: CheckInfo) {
    Row(
        modifier = Modifier.padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { isChecked -> checkInfo.onCheckedChange(isChecked) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun BtnCalculate(navController: NavController, scale: String, frets: String) {
    var openMinimalDialog by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (scale.isNotBlank() && frets.isNotBlank()){
               navController.navigate(ScreenRoot.ResultsScreen.createRoute(scale, frets))
            } else {
                openMinimalDialog = true
            }
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF2D172F)
        )
    ) {
        Text(text = stringResource(R.string.btn_calculate))
    }
    // Mostrar el diálogo si la condición es verdadera
    if (openMinimalDialog) {
        MinimalDialog(onDismissRequest = { openMinimalDialog = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalTopAppBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(R.string.topbar_title_calculate), style = TextStyle(fontSize = 18.sp)) },
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
fun CalculateScreenPrev() {
    //CalculateScreen(navController = TODO())
}