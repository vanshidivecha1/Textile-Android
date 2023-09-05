package com.ib.textileecommerce.networking

import com.google.gson.JsonObject
import com.ib.textileecommerce.model.UserBean
import com.ib.textileecommerce.response.AppResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkService(private val networkRequest: NetworkRequest) {
    suspend fun requestLogin(jsonObject: JsonObject): AppResponse<UserBean> =
        withContext(Dispatchers.IO) { networkRequest.requestLoginAsync(jsonObject).await() }

}