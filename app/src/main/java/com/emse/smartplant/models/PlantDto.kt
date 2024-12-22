package com.emse.smartplant.models

data class PlantDto(
    val id: Long,
    var name: String,
    val plant_type: String,
    var current_humidity: Double,
    var current_temperature: Double,
    var current_enlightment : Double,
    val min_humidity: Double,
    val max_humidity: Double

)