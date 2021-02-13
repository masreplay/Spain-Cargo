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
    @SerializedName("managedEmployees")
    val managedEmployees: List<Employee>,
    @SerializedName("totalWorkdays")
    val totalWorkdays: Int,
    @SerializedName("vacation")
    val vacation: Int,
    @SerializedName("pastMonthDiffPercentage")
    val pastMonthDiffPercentage: Int

){

    fun getPresentPercentage():String{

        return String.format("%.2f",(present.toDouble()/totalWorkdays.toDouble())*100.0)+"%"
    }
    fun getVacationPercentage():String{
        return String.format("%.2f",(vacation.toDouble()/totalWorkdays.toDouble())*100.0)+"%"
    }
    fun getAbsentPercentage():String{

        return String.format("%.2f",(absent.toDouble()/totalWorkdays.toDouble())*100.0)+"%"
    }
    fun getDelayPercentage():String{

        return String.format("%.2f", (delay.toDouble()/totalWorkdays.toDouble())*100.0)+"%"
    }
}