package com.emse.smartplant.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PlantViewModel: ViewModel() {
    var plant by mutableStateOf <PlantDto?>(null)
}