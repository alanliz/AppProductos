package com.unison.appproductos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unison.appproductos.model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false)
    abstract class ProductDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDatabaseDao
}

