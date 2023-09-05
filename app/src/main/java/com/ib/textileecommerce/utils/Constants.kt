package com.ib.textileecommerce.utils

object Constants {
    const val TIME_OUT: Long = 60
    const val CACHE_TIME = 432000

    const val API_KEY = "Authorization"
    const val DEVICE_ID = "DeviceID"
    const val DEVICE_TYPE = "DeviceType"
    const val VERSION_NAME = "versionName"
    const val TIME_ZONE = "TimeZone"

    private const val PROD = "https://magaswalaapi.magaswala.com/public/api/"
    var BASE = PROD

    // Api Keys
    const val LOGIN = "login"

    // Session Manager Keys
    const val KEY_SESSION_TOKEN = "KEY_SESSION_TOKEN"
    const val KEY_INTERNET_AVAILABLE = "KEY_INTERNET_AVAILABLE"
    const val KEY_IS_LOGIN = "KEY_IS_LOGIN"
    const val KEY_USER_TYPE = "KEY_USER_TYPE"
    const val KEY_USER_BEAN = "KEY_USER_BEAN"
    const val KEY_USER_ID = "KEY_USER_ID"

    //FontType
    const val SEGOE_REGULAR = 0
    const val SEGOE_LIGHT = 1
    const val SEGOE_SEMI_BOLD = 2
    const val SEGOE_BOLD = 3
    const val SEGOE_LIGHT_ITALIC = 4

}