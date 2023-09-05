package com.ib.textileecommerce.networking

import com.google.gson.JsonObject
import com.ib.textileecommerce.model.UserBean
import com.ib.textileecommerce.response.AppResponse
import com.ib.textileecommerce.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkRequest {

    @POST(Constants.LOGIN)
    fun requestLoginAsync(@Body jsonObject: JsonObject): Deferred<AppResponse<UserBean>>
}