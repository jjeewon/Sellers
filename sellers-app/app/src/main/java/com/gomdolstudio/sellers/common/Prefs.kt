package com.gomdolstudio.sellers.common
import com.gomdolstudio.sellers.App

object Prefs {
    private const val TOKEN = "token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_NAME = "user_name"
    private const val USER_ID = "user_id"

    val prefs by lazy {
        App.instance.getSharedPreferences(TOKEN, 0)
    }

    var token
        get() = prefs.getString(TOKEN, null)
        set(value) = prefs.edit()
                .putString(TOKEN, value)
                .apply()

    var refreshToken
        get() = prefs.getString(REFRESH_TOKEN, null)
        set(value) = prefs.edit()
                .putString(REFRESH_TOKEN, value)
                .apply()

    var userName
        get() = prefs.getString(USER_NAME, null)
        set(value) = prefs.edit()
                .putString(USER_NAME, value)
                .apply()

    var userId
        get() = prefs.getLong(USER_ID, 0)
        set(value) = prefs.edit()
                .putLong(USER_ID, value)
                .apply()

}