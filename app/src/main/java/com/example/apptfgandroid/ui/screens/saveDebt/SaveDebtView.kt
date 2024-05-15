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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectContact(
    state: State<SaveDebtState>,
    contactSelected: UserDTO,
    onContactSelected: (UserDTO) -> Unit,
    onIsContactSelectedChange: (Boolean) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    var contact by remember { mutableStateOf("") }


    val country = arrayOf("India", "USA", "China", "Japan", "India", "USA", "China",
        "Japan","India", "USA", "China", "Japan","India", "USA", "China", "Japan",
        "India", "USA", "China", "Japan", "India", "USA", "China")


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fieldValue = if(isSelected) contactSelected.username else contact
            IconButton(
                onClick = { isExpanded = !isExpanded },
            ) {
                val iconExpanded = if(isExpanded) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp
                Icon(
                    imageVector = iconExpanded,
                    contentDescription = "More"
                )
            }
            TextField(
                value = fieldValue,
                onValueChange = {
//                    isSelected = false
                    contact = it
                },
                label = { Text("Buscar contacto") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            val iconIsSelected = if(!isSelected) Icons.Outlined.Search else Icons.Outlined.Check
            Icon(
                modifier = Modifier.padding(start = 10.dp),
                imageVector = iconIsSelected,
                contentDescription = "More"
            )
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
                        isSelected = true
                        onIsContactSelectedChange(isSelected)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectContact(
            state = state,
            contactSelected = contactSelected,
            onContactSelected ={
                contactSelected = it
            },
            onIsContactSelectedChange = {
                isContactSelected = it
            }
        )


        OutlinedTextField(
            modifier = Modifier.padding(top = 10.dp),
            value = description,
            onValueChange ={ description = it },
            label = { Text("Descripción") },
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 15.dp),
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Importe") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

        Button(
            modifier = Modifier.padding(top = 15.dp).width(120.dp),
            enabled = isContactSelected and !amount.equals(""),
            onClick = {
                try {
                    val amountDouble = amount.toDouble()
                    if(description.length > 30){
                        description = ""

                    } else {
                        val creatDdebt = CreateDebtDTO(
                            contactSelected.username,
                            amountDouble,
                            description,
                            false,
                        )
                        state.value.saveDebt(creatDdebt)
                    }
                } catch (e: NumberFormatException) {
                    amount = ""
                }

            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Outlined.Add,"Add",
//                    modifier = Modifier.weight(0.4f)
                )
                Text(
                    text = "Crear",
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}