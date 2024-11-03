package com.unison.appproductos.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unison.appproductos.model.Product
import com.unison.appproductos.state.ProductState
import com.unison.appproductos.room.ProductsDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductViewModel(private val dao: ProductsDatabaseDao): ViewModel() {

    // Estado del modelo
    var estado by mutableStateOf(ProductState())
        private set

    // Flags para controlar los diálogos
    var productoAgregadoExitosamente by mutableStateOf(false)
        private set

    var productoModificadoExitosamente by mutableStateOf(false)
        private set

    // Inicializar el ViewModel
    init {
        viewModelScope.launch {
            // Simular carga de datos
            dao.getProducts().collectLatest {
                estado = estado.copy(productsList = it)
            }
            delay(2000)
        }
    }

    // Método para agregar un producto
    fun addProduct(product: Product) {
        if (product.nombre.isBlank() || product.descripcion.isBlank() || product.precio == 0.0)
            return

        CoroutineScope(Dispatchers.IO).launch {
            dao.addProduct(product)
            // Marcar que el producto fue agregado exitosamente
            productoAgregadoExitosamente = true
        }
    }

    // Método para actualizar un producto
    fun updateProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateProduct(product)
            // Marcar que el producto fue modificado exitosamente
            productoModificadoExitosamente = true
        }
    }

    // Método para eliminar un producto
    fun deleteProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProduct(product)
        }
    }

    // Método para obtener un producto por ID
    fun getProductById(id: Int?): Product? {
        return runBlocking {
            dao.getProductById(id ?: 0).firstOrNull()
        }
    }

    // Método para obtener el flujo de un producto por ID
    fun getProductFlowById(id: Int?): Flow<Product> {
        return dao.getProductById(id ?: 0)
    }

    // Método para resetear los flags después de que se muestre el diálogo
    fun resetFlags() {
        productoAgregadoExitosamente = false
        productoModificadoExitosamente = false
    }



}





//package com.unison.appproductos.viewModels
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.unison.appproductos.model.Product
//import com.unison.appproductos.state.ProductState
//import com.unison.appproductos.room.ProductsDatabaseDao
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//
//class ProductViewModel(private val dao: ProductsDatabaseDao): ViewModel() {
//
//    // Estado del modelo
//    var estado by mutableStateOf(ProductState())
//        private set
//
//    // Inicializar el ViewModel
//    init {
//        viewModelScope.launch {
//            // Simular carga de datos
//            dao.getProducts().collectLatest{
//                estado = estado.copy(productsList = it)
//            }
//            delay(2000)
//
//        }
//    }
//
//
//    fun addProduct(product: Product){
//
//        if(product.nombre.isBlank() || product.descripcion.isBlank() || product.precio == 0.0)
//            return;
//
//        CoroutineScope(Dispatchers.IO).launch {
//            dao.addProduct(product)
//        }
//    }
//
//    fun updateProduct(product: Product){
//        CoroutineScope(Dispatchers.IO).launch {
//            dao.updateProduct(product)
//        }
//    }
//
//    fun deleteProduct(product: Product){
//        CoroutineScope(Dispatchers.IO).launch {
//            dao.deleteProduct(product)
//        }
//    }
//    fun getProductById(id: Int?): Product? {
//        return runBlocking {
//            dao.getProductById(id ?: 0).firstOrNull() // Suspendido para obtener el valor actual
//        }
//    }
//    fun getProductFlowById(id: Int?): Flow<Product> {
//        return dao.getProductById(id ?: 0)
//    }
//
//
//}
