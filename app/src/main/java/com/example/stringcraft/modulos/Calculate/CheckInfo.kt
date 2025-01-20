package com.example.stringcraft.modulos.Calculate

data class CheckInfo(
    val title: String,
    var selected: Boolean = false,
    var onCheckedChange:(Boolean) -> Unit
)
