package com.example.apptfgandroid.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.models.debt.DebtDTO

@Composable
fun DebtCard(
    debt: DebtDTO,
    onPayOffDebt: () -> Unit,
    debtToSendNotification: DebtDTO,
    onDebtToSendNotificationChange: (DebtDTO) -> Unit
) {
    var backgroundColor: Color
    var fontColor: Color
    if(debtToSendNotification == debt){
        backgroundColor = MaterialTheme.colorScheme.primary
        fontColor = Color.White
    } else {
        backgroundColor = Color.White
        fontColor = MaterialTheme.colorScheme.primary
    }




    var modifier = Modifier
        .fillMaxWidth()
        .padding(top=10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
        .background(
            color = backgroundColor,
            shape = RoundedCornerShape(4.dp)
        )
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(4.dp)
        )

    if(debt.isCreditor and !debt.isPaid){
        modifier = modifier.clickable {
            if(debtToSendNotification == DebtDTO.empty()) onDebtToSendNotificationChange(debt) else onDebtToSendNotificationChange(DebtDTO.empty())
        }
    }

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .weight(0.15f)
                    .height(50.dp)

            ){
                if(debt.isPaid){
                    Icon(
                        imageVector = Icons.Outlined.Check, contentDescription = "", tint = fontColor, modifier = Modifier.padding(start= 8.dp))

                } else{
                    Icon(
                        imageVector = rememberPendingActions(), contentDescription = "", tint = fontColor)
                }
            }

            val colorAmount = if (debt.isCreditor) Color.Green else Color.Red
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Box{
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${debt.counterpartyUser.firstName} ${debt.counterpartyUser.lastName}",
                                color = fontColor)
                        }

                    }
                    Text(text = debt.counterpartyUser.username, color = fontColor)
                }
                Divider(color = fontColor, thickness = 2.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = String.format("%.2f", debt.amount),
                        color = colorAmount
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = debt.description,
                        color = fontColor
                    )
                }
            }
            if(!debt.isPaid && debt.isCreditor){
                Box(
                    modifier = Modifier
                ) {
                    IconButton(
                        onClick = {
                            onPayOffDebt()
                                  },
                        ) {
                        Icon(imageVector = Icons.Outlined.Send, contentDescription = "", tint = fontColor)
                    }
                }
            }
        }
    }
}


@Composable
fun rememberPendingActions(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "pending_actions",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(28.875f, 36.375f)
                quadToRelative(-3.25f, 0f, -5.542f, -2.292f)
                quadToRelative(-2.291f, -2.291f, -2.291f, -5.541f)
                quadToRelative(0f, -3.25f, 2.291f, -5.542f)
                quadToRelative(2.292f, -2.292f, 5.542f, -2.292f)
                reflectiveQuadTo(34.417f, 23f)
                quadToRelative(2.291f, 2.292f, 2.291f, 5.542f)
                reflectiveQuadToRelative(-2.291f, 5.541f)
                quadToRelative(-2.292f, 2.292f, -5.542f, 2.292f)
                close()
                moveToRelative(2.5f, -3.75f)
                lineToRelative(1.125f, -1.167f)
                lineToRelative(-3.083f, -3.041f)
                verticalLineToRelative(-4.625f)
                horizontalLineToRelative(-1.625f)
                verticalLineTo(29f)
                close()
                moveTo(7.917f, 34.333f)
                quadToRelative(-1.084f, 0f, -1.855f, -0.771f)
                quadToRelative(-0.77f, -0.77f, -0.77f, -1.854f)
                verticalLineTo(8f)
                quadToRelative(0f, -1.083f, 0.687f, -1.854f)
                quadToRelative(0.688f, -0.771f, 1.396f, -0.771f)
                horizontalLineToRelative(8f)
                quadToRelative(0.333f, -1.458f, 1.479f, -2.396f)
                quadTo(18f, 2.042f, 19.5f, 2.042f)
                reflectiveQuadToRelative(2.646f, 0.937f)
                quadToRelative(1.146f, 0.938f, 1.479f, 2.396f)
                horizontalLineToRelative(8f)
                quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                quadToRelative(0.771f, 0.771f, 0.771f, 1.854f)
                verticalLineToRelative(11.583f)
                quadToRelative(-0.667f, -0.416f, -1.292f, -0.666f)
                quadToRelative(-0.625f, -0.25f, -1.333f, -0.459f)
                verticalLineTo(8f)
                horizontalLineTo(27.5f)
                verticalLineToRelative(5.125f)
                horizontalLineTo(12.042f)
                verticalLineTo(8f)
                horizontalLineTo(7.917f)
                verticalLineToRelative(23.708f)
                horizontalLineToRelative(10.958f)
                quadToRelative(0.208f, 0.709f, 0.5f, 1.313f)
                quadToRelative(0.292f, 0.604f, 0.75f, 1.312f)
                close()
                moveTo(20f, 7.792f)
                quadToRelative(0.667f, 0f, 1.146f, -0.479f)
                quadToRelative(0.479f, -0.48f, 0.479f, -1.146f)
                quadToRelative(0f, -0.667f, -0.479f, -1.125f)
                quadToRelative(-0.479f, -0.459f, -1.104f, -0.459f)
                quadToRelative(-0.709f, 0f, -1.167f, 0.459f)
                quadToRelative(-0.458f, 0.458f, -0.458f, 1.125f)
                quadToRelative(0f, 0.666f, 0.458f, 1.146f)
                quadToRelative(0.458f, 0.479f, 1.125f, 0.479f)
                close()
            }
        }.build()
    }
}