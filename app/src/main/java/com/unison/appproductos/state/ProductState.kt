package com.unison.appproductos.state

import com.unison.appproductos.model.Product

data class ProductState(
//    val productos: List<Product> = listOf(),
    val estaCargando: Boolean = false,
    val productsList: List<Product> = emptyList()
)