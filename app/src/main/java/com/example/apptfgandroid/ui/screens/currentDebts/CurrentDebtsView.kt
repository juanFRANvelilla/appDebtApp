package com.example.apptfgandroid.ui.screens.currentDebts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.models.DebtDTO
import com.example.apptfgandroid.models.UserDTO

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.ui.common.composables.BottomBar
import com.example.apptfgandroid.ui.common.composables.DebtCard
import com.example.apptfgandroid.ui.common.composables.PaymentOption
import com.example.apptfgandroid.ui.common.composables.SelectableBox
import com.example.apptfgandroid.ui.screens.mainMenu.MainMenuContent
import com.example.apptfgandroid.ui.screens.mainMenu.TopBar
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
        isCreditor = false,
        counterpartyUser =  userDTO,
        amount = 1000.0,
        date = LocalDate.now(),
        description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
        isPaid = true
    )
//    DebtCard(debtDTO)
    CurrentDebtsView(null)


}






@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentDebtsView(navController: NavHostController?) {
    Scaffold (
        content = { CurrentDebtsContent() },
        bottomBar = { BottomBar(navController) }
    )
}






@Composable
fun CurrentDebtsContent(){
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
            isCreditor = false,
            counterpartyUser =  userDTO,
            amount = 1000.0,
            date = LocalDate.now(),
            description = "Pago de préstamo Pago de préstamo Pago de préstamoPago de préstamoPago de préstamoPago de préstamoPago de préstamo",
            isPaid = false
        )

        val debtList = listOf<DebtDTO>(debtDTO)

        val filteredDebts: List<DebtDTO> = when (selectedOption) {
            PaymentOption.Owe -> debtList.filter { !it.isCreditor }
            else -> debtList.filter { it.isCreditor }
        }

        LazyColumn() {
            items(filteredDebts) { debt ->

                DebtCard(debt = debt)
            }
        }

    }
}









