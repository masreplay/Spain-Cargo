package com.spain_cargo.cargo.data.db

import androidx.room.*
import com.spain_cargo.cargo.data.model.Item
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)


    @get:Query("SELECT * from Item order by id desc")
    val allCategories: Single<List<Item>>

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM Item")
    fun nukeTable(): Completable

}