package com.spain_cargo.cargo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spain_cargo.cargo.data.model.Item

@Database(
    entities = [Item::class],
    version = 3
)

abstract class ItemDb : RoomDatabase() {

    abstract fun itemDao(): ItemDao


}