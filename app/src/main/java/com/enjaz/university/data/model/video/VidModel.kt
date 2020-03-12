package com.enjaz.university.data.model.video

import com.enjaz.university.data.model.video.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VidModel {
    @SerializedName("movie")
    @Expose
    var movie: Movie? = null

}