package com.enjaz.university.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyItem : Serializable {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("picture")
    @Expose
    var picture: String? = null
    @SerializedName("pictureSmall")
    @Expose
    var pictureSmall: String? = null
    @SerializedName("pictureMedium")
    @Expose
    var pictureMedium: String? = null
    @SerializedName("pictureBig")
    @Expose
    var pictureBig: String? = null
    @SerializedName("pictureXl")
    @Expose
    var pictureXl: String? = null
    @SerializedName("preferred")
    @Expose
    var preferred: Boolean? = null
    @SerializedName("likes")
    @Expose
    var likes: Int? = null
    @SerializedName("fans")
    @Expose
    var fans: Int? = null
    @SerializedName("comments")
    @Expose
    var comments: Int? = null
    @SerializedName("views")
    @Expose
    var views: Int? = null

    @SerializedName("genreArtist")
    fun setId(id: String?) {
        this.id = id
    }

}