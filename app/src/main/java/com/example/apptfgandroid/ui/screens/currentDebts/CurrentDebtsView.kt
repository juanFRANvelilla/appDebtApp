package com.example.apptfgandroid.ui.screens.currentDebts

import android.annotation.SuppressLint
import android.graphics.Color.alpha
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptfgandroid.models.DebtDTO
import com.example.apptfgandroid.models.UserDTO
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.apptfgandroid.ui.common.ItemBottomNav
import com.example.apptfgandroid.ui.common.composables.BottomBar
import com.example.apptfgandroid.ui.common.composables.BottomSheetContent
import com.example.apptfgandroid.ui.common.composables.DebtCard
import com.example.apptfgandroid.ui.common.composables.PaymentOption
import com.example.apptfgandroid.ui.common.composables.SelectableBox
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

@Composable
@Preview
fun debt(){
    val userDTO = UserDTO(
        username = "+346354532",
        firstName = "Juanfran",
        lastName = "Velilla",
        email = "ejemplo@correo.com"
    )

    val debtDTO = DebtDTO(
        id = 2,
        isCreditor = false,
        counterpartyUser =  userDTO,
        amount = 1000.0,
        date = LocalDate.now().toString(),
        description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
        isPaid = true
    )
    CurrentDebtsView(null)

}






@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CurrentDebtsView(navController: NavHostController?) {
    val scope = rememberCoroutineScope()
    val viewModel: CurrentDebtsViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var debtIdToPayOff by remember { mutableStateOf(0) }


    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(
                message = "¿Estás seguro de que quieres pagar esta deuda?",
                onDismiss = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                },
                onAccept = {
                    state.payOffDebt(debtIdToPayOff)
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }
            )
        }
    ){
        Scaffold (
            content = {
                CurrentDebtsContent(
                    state,
                    onShowBottomSheetChange = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    },
                    onDebtToPayOffChange = {
                        debtIdToPayOff = it
                    }
                )
            },
            bottomBar = { BottomBar(navController, ItemBottomNav.CurrentDebts.title) }
        )
    }


}

@Composable
fun CurrentDebtsContent(
    state: CurrentDebtsState,
    onShowBottomSheetChange: (Boolean) -> Unit,
    onDebtToPayOffChange: (Int) -> Unit

) {
    var selectedOption by remember { mutableStateOf(PaymentOption.Owe) }

    Column(){
        SelectableBox(
            selectedOption
        ) {
            selectedOption = it
        }

        val userDTO = UserDTO(
            username = "+346354532",
            firstName = "Juanfran",
            lastName = "Velilla",
            email = "ejemplo@correo.com"
        )

        val debtDTO = DebtDTO(
            id = 2,
            isCreditor = false,
            counterpartyUser =  userDTO,
            amount = 1000.0,
            date = LocalDate.now().toString(),
            description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
            isPaid = false
        )

//        val debtList = listOf<DebtDTO>(debtDTO)
        val debtList = state.debtList

        val filteredDebts: List<DebtDTO> = when (selectedOption) {
            PaymentOption.Owe -> debtList.filter { !it.isCreditor }
            else -> debtList.filter { it.isCreditor }
        }

        LazyColumn() {
            items(filteredDebts) { debt ->
                DebtCard(
                    debt = debt,
                    onPayOffDebt = {
                        onShowBottomSheetChange(true)
                        onDebtToPayOffChange(debt.id)
//                        state.payOffDebt(debt.id)
                    }
                )
            }
        }
    }
}









