package com.enjaz.hr.data.model.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Category {
    @PrimaryKey
    @SerializedName("slug")
    @Expose
    var slug: String=""

    @SerializedName("name")
    @Expose
    var categoryName: String? = null

}