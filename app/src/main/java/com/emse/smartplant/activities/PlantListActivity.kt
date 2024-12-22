package com.emse.smartplant.activities



import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.emse.smartplant.models.PlantDto
import com.emse.smartplant.services.PlantService
import com.emse.smartplant.ui.theme.PurpleGrey80
import com.emse.smartplant.ui.theme.SmartPlantTheme


class PlantListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartPlantTheme() {
                Surface(modifier = Modifier.padding(16.dp)) {
                    RoomListDisplay()
                }
            }
        }
    }

}

@Composable
fun RoomListDisplay() {
    val plants = PlantService.findAll()
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(plants, key = { it.id }) { plant ->
            PlantItem(
                plant = plant,
                modifier = Modifier.clickable { openPlant(context, plant.name) }
            )
        }
    }
}

fun openPlant(context: android.content.Context, plantParam: String) {
    val intent = Intent(context, PlantActivity::class.java).apply {
        putExtra(MainActivity.PLANT_PARAM, plantParam)
    }
    context.startActivity(intent)
}


@Composable
fun PlantItem(plant: PlantDto, modifier: Modifier = Modifier) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, PurpleGrey80)
    ) {
        Row(
            modifier = modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = plant.plant_type,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Current humidity : " + (plant.current_humidity.toString() ?: "?") + "%",
                    style = MaterialTheme.typography.bodySmall
                )


            }
            Text(
                text = (plant.name ?: "?") ,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomItemPreview() {
    SmartPlantTheme() {
        PlantItem(PlantService.PLANTS[0])
    }
}
