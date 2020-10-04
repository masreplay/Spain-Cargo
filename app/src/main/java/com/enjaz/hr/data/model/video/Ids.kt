package com.enjaz.hr.data.model.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Ids {
    @PrimaryKey
    @SerializedName("trakt")
    @Expose
    var trakt: Int = -1
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("imdb")
    @Expose
    var imdb: String? = null
    @SerializedName("tmdb")
    @Expose
    var tmdb: Int? = null

}