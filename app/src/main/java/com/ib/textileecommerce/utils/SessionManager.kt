package com.ib.textileecommerce.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.ib.textileecommerce.utils.Constants.KEY_INTERNET_AVAILABLE
import com.ib.textileecommerce.utils.Constants.KEY_IS_LOGIN
import com.ib.textileecommerce.utils.Constants.KEY_SESSION_TOKEN

class SessionManager(context: Context) {
    private var mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


    fun getAccessToken(): String {
        return mPrefs.getString(KEY_SESSION_TOKEN, "")!!
    }

    fun setAccessToken(firebaseToken: String) {
        val e = mPrefs.edit()
        e.putString(KEY_SESSION_TOKEN, firebaseToken)
        e.apply()
    }

    fun setInternetAvailable(isInternetAvail: Boolean) {
        val e = mPrefs.edit()
        e.putBoolean(KEY_INTERNET_AVAILABLE, isInternetAvail)
        e.apply()
        Log.w("InternetMonitor ", "onAvailable " + mPrefs.getBoolean(KEY_INTERNET_AVAILABLE, false))
    }

    fun isLoginUser(): Boolean {
        return mPrefs.getBoolean(KEY_IS_LOGIN, false)
    }

    fun setLoginUser(isLogin: Boolean) {
        val e = mPrefs.edit()
        e.putBoolean(KEY_IS_LOGIN, isLogin)
        e.apply()
    }

    fun logout() {
        mPrefs.edit().clear().apply()
    }
}