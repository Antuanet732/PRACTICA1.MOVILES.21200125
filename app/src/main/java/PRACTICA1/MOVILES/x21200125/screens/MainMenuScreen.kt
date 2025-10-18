package PRACTICA1.MOVILES.x21200125.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Práctica Calificada 01", fontSize = 24.sp, modifier = Modifier.padding(bottom = 24.dp))

        Button(onClick = { navController.navigate("water_calculator") }, modifier = Modifier.fillMaxWidth()) {
            Text("Calculadora de consumo de agua")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("activity_tracker") }, modifier = Modifier.fillMaxWidth()) {
            Text("Registro de actividad física")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("car_catalog") }, modifier = Modifier.fillMaxWidth()) {
            Text("Catálogo de Autos deportivos")
        }
    }
}