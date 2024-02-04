package com.example.apptfgandroid.ui.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.models.CreateUserDTO

@Composable
fun ResponseMessageDialog(
    onDismiss: () -> Unit,
    status: String,
    message: String
) {

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Text (
                    text = message,
                    color = if (status.equals("error")) {
                        Color.Red
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                    modifier = Modifier.padding(8.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    TextButton(
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun Response(){
//    ResponseMessageDialog(onDismiss = {}, message = "Error, te has quedado sin intentos para verificar tu numero de telefono")
//}


