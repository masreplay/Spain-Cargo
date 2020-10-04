package com.enjaz.hr.data.model.video

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Movie {
    @PrimaryKey
    @SerializedName("movieID")
    @Expose
    var movieId: Int = -1
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("year")
    @Expose
    var year: Int? = null
    @Ignore
    @SerializedName("ids")
    @Expose
    var ids: Ids? = null
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("released")
    @Expose
    var released: String? = null
    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("trailer")
    @Expose
    var trailer: String? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("rating")
    @Expose
    var rating: Double? = null

    @SerializedName("votes")
    @Expose
    var votes: Int? = null
    @SerializedName("comment_count")
    @Expose
    var commentCount: Int? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
//    @SerializedName("available_translations")
//    @Expose
//    var availableTranslations: List<String>? = null
    @SerializedName("genres")
    @Expose
    var genres: List<String>? = null
    @SerializedName("certification")
    @Expose
    var certification: String? = null
    @SerializedName("movieType")
    @Expose
    var movieType: String? = null

    var ratingString: Double?=null
    get() {
        return if (rating.toString().length>3)
            rating.toString().substring(0,3).toDouble()
        else rating
    }
    val categoryesString: String
        get(){

        var formedStr: String =genres?.get(0)?:""
            genres?.forEachIndexed { index, element ->
                if (index>2){
                    return@forEachIndexed
                }
                if (index>0)
                formedStr="$formedStr / $element"



            }
        return formedStr
    }

}