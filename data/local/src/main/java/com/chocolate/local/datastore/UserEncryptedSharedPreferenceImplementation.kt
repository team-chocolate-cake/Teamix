package com.chocolate.local.datastore

import android.content.SharedPreferences
import com.chocolate.repository.datastore.UserEncryptedSharedPreference
import javax.inject.Inject

class UserEncryptedSharedPreferenceImplementation @Inject constructor(
        private val sharedPreferences: SharedPreferences
): UserEncryptedSharedPreference {

    override fun putAuthenticationData(apikey: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(API_KEY, apikey)
        editor.putString(EMAIL, email)
        editor.apply()
    }

    override fun getApiKey(): String {
        return sharedPreferences.getString(API_KEY, null) ?: ""
    }

    override fun getEmail(): String {
        return sharedPreferences.getString(EMAIL, null) ?: ""
    }

    override fun deleteAuthenticationData() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }

    private companion object {
        const val API_KEY = "API_KEY"
        const val EMAIL = "EMAIL"
    }

}