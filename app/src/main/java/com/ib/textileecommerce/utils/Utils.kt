package com.ib.textileecommerce.utils

import java.util.Calendar
import java.util.TimeZone

object Utils {
    private val TAG = Utils::class.simpleName

    fun getTimeZone(): String {
        val calendar = Calendar.getInstance()
        val timeZone = calendar.timeZone

        // return timeZone.displayName.toString()
        return timeZone.getDisplayName(false, TimeZone.SHORT).toString()
    }
}