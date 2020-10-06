package com.enjaz.hr.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enjaz.hr.util.Converters
import com.enjaz.hr.data.model.video.Category
import com.enjaz.hr.data.model.video.Ids
import com.enjaz.hr.data.model.video.Movie

@Database(
    entities = [Movie::class, Ids::class, Category::class],
    version = 1
)
@TypeConverters(
    Converters::class
)
abstract class MovieDB: RoomDatabase() {

    abstract fun listsDao(): ListsDao?
    abstract fun categoryDao(): CategoryDao?


}