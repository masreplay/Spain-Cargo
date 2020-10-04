package com.enjaz.hr.data.model.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VidModel {
    @SerializedName("movie")
    @Expose
    var movie: Movie? = null

}