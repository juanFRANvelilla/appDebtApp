package com.example.apptfgandroid.ui.screens.historyContactDebts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.user.UserDTO
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.apptfgandroid.ui.common.composables.BottomSheetContent
import com.example.apptfgandroid.ui.common.composables.DebtCard
import com.example.apptfgandroid.ui.common.composables.PaymentOption
import com.example.apptfgandroid.ui.common.composables.SelectableBox
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HistoryContactDebtsView(navController: NavHostController?, counterpartyUsername: String) {
    val scope = rememberCoroutineScope()
    val viewModel: HistoryContactDebtsViewModel = getViewModel(parameters = { parametersOf(counterpartyUsername) })
    val state by viewModel.state.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var debtIdToPayOff by remember { mutableStateOf(0) }
    var debtToSendNotification by remember { mutableStateOf(DebtDTO.empty()) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(
                message = "¿Estás seguro de que quieres dejar pagada esta deuda?",
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
        },
        sheetShape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
    ){
        Scaffold (
            content = {innerPadding ->
                CurrentDebtsContent(
                    state,
                    onShowBottomSheetChange = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    },
                    onDebtToPayOffChange = {
                        debtIdToPayOff = it
                    },
                    debtToSendNotification = debtToSendNotification,
                    onDebtToSendNotificationChange = {
                        debtToSendNotification = it
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            },
            topBar = { HistoricalContactDebtsTopBar(navController, counterpartyUsername) },
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricalContactDebtsTopBar(
    navController: NavController?,
    counterpartyUsername: String
){
    TopAppBar(
        title = { Text(text = "Historial con $counterpartyUsername") },
        navigationIcon = {
            IconButton(onClick = {
                navController?.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun CurrentDebtsContent(
    state: HistoryContactDebtsState,
    onShowBottomSheetChange: (Boolean) -> Unit,
    onDebtToPayOffChange: (Int) -> Unit,
    debtToSendNotification: DebtDTO,
    onDebtToSendNotificationChange: (DebtDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedOption by remember { mutableStateOf(PaymentOption.Owe) }

    val userDTO = UserDTO(
        username = "+346354532",
        firstName = "Juanfran",
        lastName = "Velilla",
        email = "ejemplo@correo.com"
    )

    val debtDTO = DebtDTO(
        id = 2,
        isCreditor = true,
        counterpartyUser =  userDTO,
        amount = 1000.0,
        date = LocalDate.now().toString(),
//            description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
        description = "Pago de préstamo",
        isPaid = false
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        SelectableBox(
            selectedOption
        ) {
            selectedOption = it
        }




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
                    },
                    debtToSendNotification = debtToSendNotification,
                    onDebtToSendNotificationChange = {
                        onDebtToSendNotificationChange(it)
                    }

                )
            }
        }

        Spacer(modifier =Modifier.weight(1f))


        if(selectedOption == PaymentOption.Owed && filteredDebts.size > 0){
            Button(
                modifier = Modifier.padding(bottom = 86.dp),
                enabled = debtToSendNotification != DebtDTO.empty(),
                onClick = {
                    state.sendNotification(DebtNotificationDTO(debtToSendNotification, ""))
                    onDebtToSendNotificationChange(DebtDTO.empty())
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Mandar recordatorio",
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Icon(Icons.Outlined.Notifications,"")
                }
            }
        }
    }
}









