package com.example.ipsports.data.model

data class CenterWithSports(
    val center: Center = Center(),
    val sports: List<Sport> = emptyList()
)