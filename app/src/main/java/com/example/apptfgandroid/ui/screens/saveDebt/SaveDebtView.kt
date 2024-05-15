package com.example.apptfgandroid.ui.screens.saveDebt

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.apptfgandroid.ui.common.composables.BottomBar
import org.koin.androidx.compose.getViewModel
import com.example.apptfgandroid.models.UserDTO

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SaveDebtView(navController: NavHostController?) {
    val viewModel: SaveDebtViewModel = getViewModel()
    val state = viewModel.state.collectAsState()
    Scaffold(
        content = { SaveDebtContent(state) } ,
        bottomBar = { BottomBar(navController) }
    )

}


@Composable
@Preview
fun prev(){
//    SaveDebtContent(navController = null)
//    SearchIcon(null, {  })
}



@Composable
fun SearchIcon(
    isExpanded: Boolean?,
    onIsExpandedChange: (Boolean) -> Unit
){
    val iconExpanded = if(isExpanded!!) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp
    IconButton(
        onClick = {
            if(isExpanded) onIsExpandedChange(false) else onIsExpandedChange(true)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val iconExpanded = if(isExpanded) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp
            Icon(
                imageVector = iconExpanded,
                contentDescription = "More"
            )
            Icon(
//            modifier = Modifier.padding(start = 10.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = "More"
            )

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectContact(
    state: State<SaveDebtState>,
    contactSelected: UserDTO,
    onContactSelected: (UserDTO) -> Unit,
    onIsContactSelectedChange: (Boolean) -> Unit,
    isContactSelected: Boolean
) {
    var isExpanded by remember { mutableStateOf(false) }
    var contact by remember { mutableStateOf("") }

//    val country = arrayOf("India", "USA", "China", "Japan", "India", "USA", "China",
//        "Japan","India", "USA", "China", "Japan","India", "USA", "China", "Japan",
//        "India", "USA", "China", "Japan", "India", "USA", "China")

    val country = arrayOf("India", "USA", "China")


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fieldValue = if(isContactSelected) contactSelected.username else contact
            SearchIcon(
                isExpanded = isExpanded,
                onIsExpandedChange = {
                    isExpanded = it
                }
            )
            TextField(
                modifier = Modifier
                    .weight(8f)
                    .padding(start = 5.dp),
                value = fieldValue,
                onValueChange = {
//                    isExpanded = true
                    contact = it
                },
                label = { Text("Buscar contacto") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            if(isContactSelected){
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1.2f),
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "More",
                    tint = Color.Green
                )
            } else {
                Spacer(modifier = Modifier.weight(1.2f))
            }

        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
//                state.value.contacts.toList().forEach { contact ->
            country.forEach { contact ->
                DropdownMenuItem(
                    modifier = Modifier.padding(start = 10.dp),
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                modifier = Modifier.weight(2f),
                                text = contact
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = contact
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = contact
                            )

                        }

                    },
                    onClick = {
//                        onContactSelected(contact)
                        isExpanded = false
                        onIsContactSelectedChange(true)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveDebtContent(state: State<SaveDebtState>) {
    var contactSelected by remember { mutableStateOf(UserDTO()) }
    var isContactSelected by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var errorDesc by remember { mutableStateOf("") }
    var errorAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectContact(
            state = state,
            isContactSelected = isContactSelected,
            onIsContactSelectedChange = {
                isContactSelected = it
            },
            contactSelected = contactSelected,
            onContactSelected ={
                contactSelected = it
            }
        )

        Column(
            horizontalAlignment = Alignment.Start
        ){
            OutlinedTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
            )
            if (!errorDesc.equals("")) {
                Text(
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                    text = errorDesc,
                    fontSize = 12.sp,
                    color = Color.Red
                )
            }
        }


        Column(
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Importe") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )

            if (!errorAmount.equals("")) {
                Text(
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                    text = errorAmount,
                    fontSize = 12.sp,
                    color = Color.Red

                )
            }
        }



        Button(
            modifier = Modifier
                .padding(top = 15.dp)
                .width(120.dp),
            enabled = isContactSelected and !amount.equals(""),
            onClick = {
                try {
                    var validDebt = true
                    val amountDouble = amount.toDouble()
                    if(amountDouble > 1000){
                        errorAmount = "El importe tiene que ser menor a 1000"
                        amount = ""
                        validDebt = false
                    }
                    if(description.length > 25){
                        description = ""
                        errorDesc = "Maximo 30 caracteres"
                        validDebt = false
                    }
                    if(validDebt) {
                        errorDesc = ""
                        errorAmount = ""
                        val creatDdebt = CreateDebtDTO(
                            contactSelected.username,
                            amountDouble,
                            description,
                            false,
                        )
                        amount = ""
                        description = ""
                        isContactSelected = false
                        state.value.saveDebt(creatDdebt)
                    }
                } catch (e: NumberFormatException) {
                    amount = ""
                    errorAmount = "Introduce un importe numérico"
                }
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Outlined.Add,"Add")
                Text(
                    text = "Crear",
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}