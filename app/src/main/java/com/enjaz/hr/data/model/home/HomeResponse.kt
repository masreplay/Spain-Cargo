package com.enjaz.hr.data.model.home


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("absent")
    val absent: Int,
    @SerializedName("basicSalary")
    val basicSalary: Int,
    @SerializedName("deduction")
    val deduction: Int,
    @SerializedName("delay")
    val delay: Int,
    @SerializedName("present")
    val present: Int,
    @SerializedName("teammates")
    val teammates: List<Teammate>,
    @SerializedName("totalWorkdays")
    val totalWorkdays: Int,
    @SerializedName("vacation")
    val vacation: Int,
    @SerializedName("pastMonthDiffPercentage")
    val pastMonthDiffPercentage: Int

){

    fun getPresentPercentage():String{

        return "${(present.toDouble()/totalWorkdays.toDouble())*100.0}%"
    }
    fun getVacationPercentage():String{

        return "${(vacation.toDouble()/totalWorkdays.toDouble())*100.0}%"
    }
    fun getAbsentPercentage():String{

        return "${(absent.toDouble()/totalWorkdays.toDouble())*100.0}%"
    }
    fun getDelayPercentage():String{

        return "${(delay.toDouble()/totalWorkdays.toDouble())*100.0}%"
    }
}