package dev.agustacandi.learn.pestsentry.utils

import android.content.Context
import android.content.SharedPreferences
import dev.agustacandi.learn.pestsentry.data.auth.LoginResponse

class PreferenceManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(ConstVal.PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    val getToken = prefs.getString(ConstVal.KEY_TOKEN, "")
    val getName = prefs.getString(ConstVal.KEY_NAME, "")
    val getEmail = prefs.getString(ConstVal.KEY_EMAIL, "")
    val getId = prefs.getString(ConstVal.KEY_ID, "")
    val getOnboardingScreen = prefs.getBoolean(ConstVal.KEY_ONBOARDING_SCREEN, false)

    fun setLoginPrefs(loginResult: LoginResponse) {
        editor.putString(ConstVal.KEY_NAME, loginResult.user.username)
        editor.putString(ConstVal.KEY_EMAIL,loginResult.user.email)
        editor.putString(ConstVal.KEY_TOKEN, loginResult.token)
        editor.putString(ConstVal.KEY_ID, loginResult.user.id)

        editor.apply()
    }

    fun updateEmailUsername(newEmail:String,newUsername : String){
        editor.remove(ConstVal.KEY_EMAIL)
        editor.remove(ConstVal.KEY_NAME)
        editor.apply()
        //replace with new one
        editor.putString(ConstVal.KEY_NAME, newUsername)
        editor.putString(ConstVal.KEY_EMAIL,newEmail)
        editor.apply()
    }

    fun setOnboardingScreenPreference() {
        editor.putBoolean(ConstVal.KEY_ONBOARDING_SCREEN, true)
        editor.apply()
    }

    fun clearAllPreferences() {
        editor.remove(ConstVal.KEY_TOKEN)
        editor.remove(ConstVal.KEY_EMAIL)
        editor.remove(ConstVal.KEY_NAME)
        editor.remove(ConstVal.KEY_ID)
        editor.apply()
    }
}