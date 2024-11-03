package  com.unison.appproductos.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.unison.appproductos.R
import com.unison.appproductos.navigation.NavDestinations
import com.unison.appproductos.AppTheme

class PresentationCardView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                PresentationCardView(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationCardView(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Color de fondo actualizado
    ) {
        // Barra de navegación superior
        TopAppBar(
            title = {
                Text(
                    text = "PRESENTACIÓN",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate(NavDestinations.HomeView) },
                    modifier = Modifier.padding(end = 64.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )

        )

        // Contenido principal
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TarjetaPresentacion()
        }
    }
}

@Composable
fun TarjetaPresentacion() {
    // Fondo de la tarjeta principal
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(450.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), // Color de la tarjeta
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen de perfil
            Image(
                painter = painterResource(id = R.drawable.yo),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre completo
            Text(text = "Alan Gilberto Lizardi Díaz", fontSize = 24.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)

            // Puesto
            Text(text = "Desarrollador Front-End", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta interna con información de contacto
            ContactInfoCard()
        }
    }
}

@Composable
fun ContactInfoCard() {
    // Fondo de la tarjeta interna
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer), // Color de la tarjeta interna
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Filas con imágenes y texto
            ContactInfoRow(imageResId = R.drawable.phonecall, info = "(662) 46-64-41-0")
            ContactInfoRow(imageResId = R.drawable.linkedin, info = "@alanlizardi")
            ContactInfoRow(imageResId = R.drawable.email, info = "alan.lizardi.diaz@gmail.com")
        }
    }
}

@Composable
fun ContactInfoRow(imageResId: Int, info: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = info, fontSize = 16.sp, color = MaterialTheme.colorScheme.onTertiaryContainer) // Color del texto
    }
}

