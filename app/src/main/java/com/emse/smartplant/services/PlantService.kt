package com.emse.smartplant.services

import androidx.core.text.isDigitsOnly
import com.emse.smartplant.models.PlantDto


object RoomService {
    val PLANT_KIND: List<String> = listOf("Succulent", "Monstera", "Orchid", "Bonzai", "Cactus")
    val PLANT_NUMBER: List<Char> = ('A'..'Z').toList()




    fun generatePlant(id: Long): PlantDto {
        val roomName = "Plant ${PLANT_NUMBER.random()}"

        return PlantDto(
            id = id,
            name = roomName,
            plant_type = PLANT_KIND.random(),
            current_temperature = (15..30).random().toDouble(),
            current_enlightment = (15..30).random().toDouble(),
            current_humidity = (0..100).random().toDouble(),
            max_humidity = 90.0,
            min_humidity = 30.0

        )
    }

    // Create 50 rooms
    val PLANTS = (1..50).map { generatePlant(it.toLong()) }.toMutableList()

    fun findAll(): List<PlantDto> {
        // return all plants sorted by name
        return PLANTS.sortedBy { it.name}
    }

    fun findById(id: Long): PlantDto? {
        // TODO return the room with the given id or null
        for (plants in PLANTS){
            if (plants.id == id){
                return plants
            }
        }
        return null
    }

    fun findByName(name: String): PlantDto? {
        // TODO return the room with the given name or null
        for (plants in PLANTS){
            if (plants.name == name){
                return plants
            }
        }
        return null
    }

}