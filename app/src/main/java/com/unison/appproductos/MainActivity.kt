package com.unison.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.unison.appproductos.navigation.NavManager
import com.unison.appproductos.room.ProductDatabase
import com.unison.appproductos.viewModels.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // Crear una instancia de la base de datos
                val database = Room.databaseBuilder(
                    applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()

                // Obtener la instancia del DAO
                val dao = database.productsDao()
                val productViewModel = ProductViewModel(dao) // Instancia del ViewModel

                // Usar Scaffold para la estructura de la interfaz
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    // Usar MaterialTheme para garantizar que el esquema de color se aplique
                    MaterialTheme {
                        NavManager(
                            productViewModel = productViewModel,
                            modifier = Modifier.padding(paddingValues) // Aplicar el padding de Scaffold
                        )
                    }
                }
            }
        }
    }
}
