package com.example.apptfgandroid.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class PaymentOption {
    Owe,
    Owed,
    Both
}

@Composable
fun SelectableBox(
    selectedOption: PaymentOption,
    onSelectedOptionChange: (PaymentOption) -> Unit
){
    val color1 = if(selectedOption == PaymentOption.Owe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val color2 = if(selectedOption == PaymentOption.Owe) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {

        val shapeLeft = GenericShape { size: Size, layoutDirection: LayoutDirection ->
            val width = size.width / 2
            val height = size.height
            moveTo(0f, height)
            lineTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, height)
            close()
        }

        val shapeRight = GenericShape { size: Size, layoutDirection: LayoutDirection ->
            val width = size.width / 2
            val height = size.height
            val diagonalLength = (width * 0.1f) // Longitud de la diagonal (hipotenusa) para formar un ángulo de 45 grados

            moveTo(width, 0f)
            lineTo(width - diagonalLength, height) // Punto final de la diagonal para que coincida con la parte izquierda
            lineTo(size.width, height)
            lineTo(size.width, 0f)
            close()
        }

        val modifierLeft = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                clip = true
                shape = shapeLeft
            }


        val modifierRight = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                clip = true
                shape = shapeRight
            }
            .background(Color.Red)

        Box(
            modifier = modifierLeft
                .clickable { onSelectedOptionChange(PaymentOption.Owe) }
                .background(color1),
        ){
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Deudas",
                color = color2,
                fontSize = 18.sp
            )
        }

        Box(
            modifier = modifierRight
                .clickable { onSelectedOptionChange(PaymentOption.Owed) }
                .background(color2),
        ){
            Text(
                modifier = Modifier.align(Alignment.BottomEnd).padding(end=10.dp),
                text = "Cobros",
                fontSize = 18.sp,
                color = color1
            )
        }

    }

}