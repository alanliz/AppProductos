package com.unison.appproductos.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.unison.appproductos.AppTheme
import com.unison.appproductos.R
import com.unison.appproductos.navigation.NavDestinations


class HomeView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme {
                val navController = rememberNavController()
                HomeView(navController = navController)
            }
        }
    }
}



@Composable
fun HomeView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), // Color de fondo general
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        // Sección del encabezado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary) // Color del encabezado
                .padding(16.dp)
        ) {
            Text(
                text = "HOME",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


        // Imagen
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la empresa",
            modifier = Modifier
                .size(220.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre de la Empresa
        Text(
            text = "Café Tería",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground // Color del texto de la empresa
        )

        Spacer(modifier = Modifier.height(128.dp))

        // Botones
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.navigate(NavDestinations.ProductListView) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Productos", color = Color.White, fontSize = 18.sp)
            }


            Button(
                onClick = {
                    navController.navigate(NavDestinations.PresentationCardView)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Tarjeta de Presentación", color = Color.White, fontSize = 18.sp)
            }
        }


        Spacer(modifier = Modifier.height(100.dp))

        // Pie de página
        Text(
            text = "Made by: Lizardi Díaz Alan Gilberto",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            modifier = Modifier.padding(16.dp)
        )
    }
}
