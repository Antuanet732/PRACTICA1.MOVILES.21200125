package PRACTICA1.MOVILES.x21200125

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import PRACTICA1.MOVILES.x21200125.navigation.AppNavigation
import PRACTICA1.MOVILES.x21200125.ui.theme.PRACTICA1Theme // Asegúrate que tu tema se llame así

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PRACTICA1Theme { // <-- Revisa el nombre de tu tema en ui.theme/Theme.kt
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}