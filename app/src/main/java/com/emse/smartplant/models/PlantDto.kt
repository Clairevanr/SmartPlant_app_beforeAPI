package com.emse.smartplant.models

data class PlantDto(
    val id: Long,
    val name: String,
    val plant_type: String,
    val current_humidity: Double,
    val current_temperature: Double,
    val current_enlightment : Double,
    val min_humidity: Double,
    val max_humidity: Double

)