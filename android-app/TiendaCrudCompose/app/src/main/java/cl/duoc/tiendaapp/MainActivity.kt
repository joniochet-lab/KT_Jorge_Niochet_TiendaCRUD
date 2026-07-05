package cl.duoc.tiendaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.duoc.tiendaapp.navigation.TiendaNavigation
import cl.duoc.tiendaapp.ui.theme.TiendaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiendaTheme {
                TiendaNavigation()
            }
        }
    }
}
