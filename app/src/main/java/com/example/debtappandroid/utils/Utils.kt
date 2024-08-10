package com.example.debtappandroid.utils

import kotlinx.coroutines.flow.MutableStateFlow

class CommonMutableStateFlow<T>(private val mutableStateFlow: MutableStateFlow<T>) : MutableStateFlow<T> by mutableStateFlow

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)