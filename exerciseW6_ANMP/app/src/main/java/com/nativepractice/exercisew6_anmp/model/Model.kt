package com.nativepractice.exercisew6_anmp.model

import com.google.gson.annotations.SerializedName

data class Motor(
    val id:String?,
    val name:String?,
    val brand:String?,
    val engine:Spec?,
    val colors:List<String>?,
    val images:String?
)
data class Spec(
    val type:String?,
    @SerializedName("displacement_cc")
    val displacementCC:String?
)