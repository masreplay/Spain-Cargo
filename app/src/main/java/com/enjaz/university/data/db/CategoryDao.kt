package com.enjaz.university.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enjaz.university.data.model.video.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(movies: List<Category>)

    @Query("DELETE FROM Category")
    fun deleteAll()

    @get:Query("SELECT * from Category")
    val allCategories: LiveData<List<Category>>


}