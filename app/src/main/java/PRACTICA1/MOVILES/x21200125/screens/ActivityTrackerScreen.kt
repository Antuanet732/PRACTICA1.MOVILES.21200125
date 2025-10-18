package PRACTICA1.MOVILES.x21200125.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityTrackerScreen(navController: NavController) {
    var activity by remember { mutableStateOf("Correr") }
    var duration by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("Media") }
    var resultMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val activities = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val intensities = listOf("Baja", "Media", "Alta")
    var expanded by remember { mutableStateOf(false) }

    val activityRates = mapOf(
        "Correr" to 10, "Caminar" to 5, "Nadar" to 8, "Ciclismo" to 7, "Yoga" to 4
    )
    val intensityFactors = mapOf(
        "Baja" to 0.8, "Media" to 1.0, "Alta" to 1.2
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Actividad Física", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        // Dropdown para Actividad
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = activity,
                onValueChange = {},
                label = { Text("Tipo de actividad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                activities.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            activity = selectionOption
                            expanded = false
                            errorMessage = ""
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it; errorMessage = "" },
            label = { Text("Duración (en minutos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // RadioButtons para Intensidad
        Text("Intensidad", style = MaterialTheme.typography.labelLarge)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            intensities.forEach { intensityOption ->
                Row(
                    Modifier.selectable(
                        selected = (intensity == intensityOption),
                        onClick = { intensity = intensityOption; errorMessage = "" }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (intensity == intensityOption),
                        onClick = { intensity = intensityOption; errorMessage = "" }
                    )
                    Text(text = intensityOption)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val durationValue = duration.toIntOrNull()

            if (duration.isBlank()) {
                errorMessage = "Todos los campos son obligatorios."
                resultMessage = ""
            } else if (durationValue == null || durationValue <= 0) {
                errorMessage = "La duración debe ser un número positivo."
                resultMessage = ""
            } else {
                val rate = activityRates[activity] ?: 0
                val factor = intensityFactors[intensity] ?: 0.0
                val calories = rate * durationValue * factor
                resultMessage = "Calorías quemadas: %.1f kcal".format(calories)
                errorMessage = ""
            }
        }) {
            Text("Calcular Calorías")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }

        if (resultMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(resultMessage, style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al Menú")
        }
    }
}