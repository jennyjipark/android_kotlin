package com.jenny.retrofits2

import com.google.gson.annotations.SerializedName

// gson으로 직렬화
data class User(
    @SerializedName("id") val id: String, // 아이디
    @SerializedName("pw") val pw: String // 비번
)
