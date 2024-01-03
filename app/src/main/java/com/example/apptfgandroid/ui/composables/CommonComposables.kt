package com.example.apptfgandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelephoneTextField(
    onPhoneNumberChanged: (String) -> Unit,
    onCountryPrefixChanged: (String) -> Unit,
    phoneNumber: String
) {
    var expanded by remember { mutableStateOf(false) }
    var countryPrefix by remember { mutableStateOf("+34") }
    val countriesMap = mapOf(
        "Spain" to "+34",
        "United States" to "+1",
        "United Kingdom" to "+44",
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ){
        OutlinedTextField(
            value = countryPrefix,
            onValueChange = {
                if (it.length <= 4) {
                    countryPrefix = it
                    onCountryPrefixChanged(it)
                }
            },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .width(125.dp)
                .padding(end = 15.dp),
            label = {  },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            trailingIcon = {
                Box(
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    },
                ) {
                    Column(

                    ){
                        Icon(
                            Icons.Default.Call,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                onPhoneNumberChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Telefono") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

    }



    if (expanded) {
        Column(
            Modifier
                .height(240.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text("Seleccione el país",
                color = MaterialTheme.colorScheme.primary)

            LazyColumn {
                items(countriesMap.keys.toList()) { country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                countryPrefix = countriesMap[country]!!
                                onCountryPrefixChanged(countriesMap[country]!!)
                                expanded = false
                            }
                            .padding(16.dp)
                    ) {
                        Text(country)
                        Spacer(modifier = Modifier.width(8.dp))
                        if (countryPrefix == countriesMap[country]!!) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "País seleccionado",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    title:String
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = title) },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}
