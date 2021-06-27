package com.amroid.dawa.model

data class UserAnalysis(
    val data: List<Data>?,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class Data(
    val analysis1: String,
    val analysis2: String,
    val analysis3: String,
    val analysis4: String,
    val heartLevel: String,
    val id: Int
)