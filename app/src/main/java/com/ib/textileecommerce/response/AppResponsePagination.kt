package com.ib.textileecommerce.response

import com.google.gson.annotations.SerializedName

class AppResponsePagination<T> {
    @SerializedName("current_page")
    var currentPage: String = ""

    @SerializedName("total")
    var total: String = ""

    @SerializedName("data")
    var data: T? = null

    @SerializedName("first_page_url")
    var firstPageUrl: String = ""

    @SerializedName("from")
    var from: String = ""

    @SerializedName("last_page")
    var lastPage: String = ""

    @SerializedName("last_page_url")
    var lastPageUrl: String = ""

    @SerializedName("next_page_url")
    var nextPageUrl: String = ""

    @SerializedName("path")
    var path: String = ""

    @SerializedName("per_page")
    var perPage: String = ""

    @SerializedName("prev_page_url")
    var prevPageUrl: String = ""

    @SerializedName("to")
    var to: String = ""

    @SerializedName("status")
    var status = false

    @SerializedName("status_code")
    var statusCode: Int = 0

    @SerializedName("message")
    var message: String = ""
}