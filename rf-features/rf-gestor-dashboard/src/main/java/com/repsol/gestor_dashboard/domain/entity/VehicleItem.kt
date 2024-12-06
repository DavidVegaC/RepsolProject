package com.repsol.gestor_dashboard.domain.entity

class VehicleItem(
    val idVehicle: Int,
    val plate: String,
    val brand: String,
    val type: String,
    val model: String,
    val year: String,
    val fuel: String,
    val codeState: Int,
    val tankCapacity: String,
    val performance: String,
    val associatedDriver: String,
    val modificationDate: String,
    val terminationDate: String,
)