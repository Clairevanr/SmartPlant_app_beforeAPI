package com.emse.smartplant.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emse.smartplant.R
import com.emse.smartplant.ui.theme.SmartPlantTheme


class PlantActivity:  ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val param = intent.getStringExtra(MainActivity.PLANT_PARAM)
        val room = PlantService.findByNameOrId(param)

        setContent {
            SmartPlantTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (room != null) {
                        PlantDetail(room, Modifier.padding(innerPadding))
                    } else {
                        NoPlant(Modifier.padding(innerPadding))
                    }

                }
            }
        }
    }
}

@Composable
fun PlantDetail(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var nameState by remember { mutableStateOf(name) }
        Text(
            text = stringResource(R.string.act_plant_name),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            nameState,
            onValueChange = { nameState = it },
            placeholder = { Text(stringResource(R.string.act_plant_name)) },
        )
    }

}

@Composable
fun NoPlant(modifier: Modifier = Modifier){
    Text(
        text = stringResource(R.string.act_plant_none),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PlantDetailPreview() {
    SmartPlantTheme() {
        PlantDetail("Android")
    }
}

