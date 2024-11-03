package com.unison.appproductos.views

import DeleteProductDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unison.appproductos.R
import com.unison.appproductos.navigation.NavDestinations
import com.unison.appproductos.viewModels.ProductViewModel
import com.unison.appproductos.model.Product
import com.unison.appproductos.dialogs.* // Importamos los diálogos que implementamos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListView(
    navController: NavHostController,
    viewModel: ProductViewModel = viewModel()
) {
    // Colores de la interfaz
    val colorFondo = MaterialTheme.colorScheme.background
    val colorTitulo = MaterialTheme.colorScheme.onBackground
    val colorLinea = Color(0xFF787A91)
    val colorTextSecondary = MaterialTheme.colorScheme.secondary

    var productToDelete: Product by remember { mutableStateOf(Product()) }
    var openDeleteDialog by remember { mutableStateOf(false) }

    // Observamos si un producto fue agregado exitosamente
    if (viewModel.productoAgregadoExitosamente) {
        AddProductDialog(onDismiss = {
            viewModel.resetFlags() // Restablecemos el estado después de mostrar el diálogo
        })
    }

    // Observamos si un producto fue modificado exitosamente
    if (viewModel.productoModificadoExitosamente) {
        UpdateProductDialog(onDismiss = {
            viewModel.resetFlags() // Restablecemos el estado después de mostrar el diálogo
        })
    }

    // Diálogo de confirmación para eliminar
    if (openDeleteDialog) {
        DeleteProductDialog(
            productName = productToDelete.nombre,
            onConfirm = {
                viewModel.deleteProduct(productToDelete)
                openDeleteDialog = false
            },
            onCancel = { openDeleteDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "INVENTARIO",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(NavDestinations.HomeView) },
                        modifier = Modifier.padding(end = 73.dp)
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavDestinations.ProductFormView)
                    // Aquí no mostramos el diálogo aún, solo navegamos al formulario
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir producto"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo)
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Productos",
                color = colorTitulo,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )

            val estado = viewModel.estado

            if (estado.estaCargando) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (estado.productsList.isEmpty()) {
                    Text(text = "No hay productos disponibles.", color = colorTitulo)
                } else {
                    LazyColumn {
                        items(estado.productsList) { product ->
                            ProductItemCard(
                                product = product,
                                colorPrimary = colorTitulo,
                                colorSecondary = colorTextSecondary,
                                onEditClick = {
                                    navController.navigate("${NavDestinations.ProductFormView}/${product.id}")
                                    // En el formulario de edición, deberás invocar viewModel.modifyProduct(product)
                                },
                                onDeleteClick = {
                                    productToDelete = product
                                    openDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductItemCard(
    product: Product,
    colorPrimary: Color,
    colorSecondary: Color,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Nombre del producto
            Text(
                text = product.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorPrimary
            )
            // Precio
            Text(
                text = "$${product.precio}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorSecondary
            )
            // Descripción
            Text(
                text = product.descripcion,
                fontSize = 14.sp,
                color = colorSecondary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Fila de botones para editar y eliminar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // Botón de editar
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = colorPrimary
                    )
                }
                // Espaciado entre botones
                Spacer(modifier = Modifier.width(8.dp))
                // Botón de eliminar
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = colorPrimary
                    )
                }
            }
        }
    }
}




