package com.example.apptfgandroid.ui.screens.saveDebt

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptfgandroid.models.DebtDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.ui.common.composables.BottomBar
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

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

    var contact by remember { mutableStateOf("") }

    val userDTO = UserDTO(
        username = "+346354532",
        firstName = "Juanfran",
        lastName = "Velilla",
        email = "ejemplo@correo.com"
    )

    val debtDTO = DebtDTO(
        isCreditor = false,
        counterpartyUser =  userDTO,
        amount = 1000.0,
        date = LocalDate.now().toString(),
        description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
        isPaid = true
    )

    val debtList = listOf(debtDTO)




//    OutlinedTextField(
//        value = searchText,
//        onValueChange = { searchText = it },
//        label = { Text("Buscar contacto") },
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Text,
//            imeAction = ImeAction.Done
//        ),
//        modifier = Modifier.fillMaxWidth()
//    )

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        println("Mostrar menu: $isExpanded")
        TextField(
            value = contact,
            onValueChange = { contact = it },
            readOnly = false,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
//            debtList.forEach { debt ->
//                    DropdownMenuItem(
//                        text = { Text(text = debt.description) },
//                        onClick = {}
//                    )
//                }
//            }
            DropdownMenuItem(
                text = { Text(text = "ejemplo") },
                onClick = {}
            )
        }
    }
}