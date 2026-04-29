package com.tomasbusfri.files.casinopark.Model.API.APIClasses


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("png")
    val png: String,
    @SerializedName("svg")
    val svg: String
)