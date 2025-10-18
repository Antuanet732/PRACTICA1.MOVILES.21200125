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

// aaaData Class para el auto
data class Car(
    val brand: String,
    val model: String,
    val price: Double,
    val imageUrl: String
)

// Lista de datos "hardcodeados"
// Lista de datos con URLs 100% funcionales (HTTPS y públicas)
val carList = listOf(
    Car("Ferrari", "SF90 Stradale", 507000.0, "https://images.pexels.com/photos/210019/pexels-photo-210019.jpeg"),
    Car("Lamborghini", "Huracán EVO", 261274.0, "https://images.pexels.com/photos/170811/pexels-photo-170811.jpeg"),
    Car("Porsche", "911 GT3", 161100.0, "https://images.pexels.com/photos/30490113/pexels-photo-30490113.jpeg"),
    Car("McLaren", "720S", 310500.0, "https://images.pexels.com/photos/10550012/pexels-photo-10550012.jpeg"),
    Car("Bugatti", "Chiron", 3300000.0, "https://images.pexels.com/photos/30326244/pexels-photo-30326244.jpeg")
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