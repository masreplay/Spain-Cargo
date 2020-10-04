package com.enjaz.hr.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enjaz.hr.data.model.video.Movie

@Dao
interface ListsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(movies: List<Movie>)

    @Query("DELETE FROM Movie")
    fun deleteAll()


    @Query("SELECT * from Movie where movieType=:type limit 40")
    fun getAllMovies(type : String):LiveData<List<Movie>>

}