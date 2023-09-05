package com.ib.textileecommerce.response

import com.google.gson.annotations.SerializedName

class AppResponse<T> {

    @SerializedName("status")
    var status: Boolean = false

    @SerializedName("status_code")
    var statusCode: Int = 0

    @SerializedName("message")
    var message: String = ""

    @SerializedName("data")
    var data: T? = null
}