package PRACTICA1.MOVILES.x21200125.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.text.NumberFormat
import java.util.*

// Data Class para el auto
data class Car(
    val brand: String,
    val model: String,
    val price: Double,
    val imageUrl: String
)

// Lista de datos "hardcodeados"
val carList = listOf(
    Car("Ferrari", "SF90 Stradale", 507000.0, "https://via.placeholder.com/150/FF0000/FFFFFF?text=Ferrari"),
    Car("Lamborghini", "Huracán EVO", 261274.0, "https://via.placeholder.com/150/FFA500/FFFFFF?text=Lambo"),
    Car("Porsche", "911 GT3", 161100.0, "https://via.placeholder.com/150/0000FF/FFFFFF?text=Porsche"),
    Car("McLaren", "720S", 310500.0, "https://via.placeholder.com/150/FF4500/FFFFFF?text=McLaren"),
    Car("Bugatti", "Chiron", 3300000.0, "https://via.placeholder.com/150/000000/FFFFFF?text=Bugatti")
)

@Composable
fun CarCatalogScreen(navController: NavController) {
    val totalCost = carList.sumOf { it.price }
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Catálogo de Autos Deportivos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(carList) { car ->
                CarCard(car, currencyFormat)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Text(
            text = "Costo Total: ${currencyFormat.format(totalCost)}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Volver al Menú")
        }
    }
}

@Composable
fun CarCard(car: Car, currencyFormat: NumberFormat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = car.imageUrl,
                contentDescription = car.model,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(car.brand, style = MaterialTheme.typography.titleMedium)
                Text(car.model, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(currencyFormat.format(car.price), style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}