package PRACTICA1.MOVILES.x21200125.screens

import androidx.compose.foundation.layout.*
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
fun WaterCalculatorScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Masculino") }
    var resultMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val genders = listOf("Masculino", "Femenino", "Sin especificar")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Consumo de Agua", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it; errorMessage = "" },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it; errorMessage = "" },
            label = { Text("Peso corporal (en kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para Género
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                readOnly = true,
                value = gender,
                onValueChange = {},
                label = { Text("Género") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                genders.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            gender = selectionOption
                            expanded = false
                            errorMessage = ""
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val weightValue = weight.toDoubleOrNull()

            if (name.isBlank() || weight.isBlank()) {
                errorMessage = "Todos los campos son obligatorios."
                resultMessage = ""
            } else if (weightValue == null || weightValue !in 5.0..200.0) {
                errorMessage = "El peso debe ser un número entre 5 y 200."
                resultMessage = ""
            } else {
                val genderFactor = when (gender) {
                    "Masculino" -> 1.02
                    "Femenino" -> 1.01
                    else -> 1.00
                }
                val liters = weightValue * 0.035 * genderFactor
                resultMessage = "$name debe beber aproximadamente %.2f litros de agua al día".format(liters)
                errorMessage = ""
            }
        }) {
            Text("Calcular Consumo")
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