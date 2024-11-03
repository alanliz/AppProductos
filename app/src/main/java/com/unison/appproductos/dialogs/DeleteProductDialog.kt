

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding


@Composable
fun DeleteProductDialog(
    productName: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Eliminar Producto",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "¿Estás seguro de que deseas eliminar el producto \"$productName\"?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    TextButton(onClick = onCancel) {
                        Text(text = "Cancelar")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onConfirm) {
                        Text(text = "Eliminar")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteProductDialogPreview() {
    DeleteProductDialog(
        productName = "Producto Ejemplo",
        onConfirm = { /* Acción de eliminar */ },
        onCancel = { /* Acción de cancelar */ }
    )
}
