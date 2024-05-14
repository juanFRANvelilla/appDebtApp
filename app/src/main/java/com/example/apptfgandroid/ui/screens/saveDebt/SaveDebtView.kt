package com.example.apptfgandroid.ui.screens.saveDebt

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.models.DebtDTO
import com.example.apptfgandroid.ui.common.composables.BottomBar
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate
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
fun SaveDebtContent(state: State<SaveDebtState>) {
    var isExpanded by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    var contact by remember { mutableStateOf("") }
    var contactSelected by remember { mutableStateOf(UserDTO()) }



    val country = arrayOf("India", "USA", "China", "Japan")


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fieldValue = if(isSelected) contactSelected.username else contact
            println("CONTACTO ELEGIDO $isSelected")
            TextField(
                value = fieldValue,
                onValueChange = {
//                    isSelected = false
                    contact = it
                                },
                label = { Text("Buscar contacto") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.wrapContentWidth(Alignment.End)
            ) {
                val iconExpanded = if(isExpanded) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp
                Icon(
                    imageVector = iconExpanded,
                    contentDescription = "More"
                )
            }
            val iconIsSelected = if(!isSelected) Icons.Outlined.Search else Icons.Outlined.Check
            Icon(
                imageVector = iconIsSelected,
                contentDescription = "More"
            )
        }

        Box(
//            modifier = Modifier
//                .width(300.dp)
//                .background(Color.Red)

        ){
            DropdownMenu(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
//                    .padding(start = 100.dp),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                state.value.contacts.toList().forEach { contact ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .padding(start = 100.dp),
                        text = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    modifier = Modifier.weight(2f),
                                    text = contact.username
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = contact.firstName
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = contact.lastName
                                )

                            }

                        },
                        onClick = {
                            contactSelected = contact
                            isExpanded = false
                            isSelected = true
                        }
                    )
                }
            }
        }

    }
}