package convertedin.pixel.pixelsdk.data.sharedPref

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesUtils private constructor(private val prefHelper: PrefHelper) {


    fun savePixelId(pixelId : String) {
        prefHelper.putString(PIXEL_ID, pixelId)
    }
    fun getPixelId(): String? {
        return prefHelper.getString(PIXEL_ID, null)
    }


    fun saveStoreUrl(storeUrl : String) {
        prefHelper.putString(STORE_URL, storeUrl)
    }
    fun getStoreUrl(): String? {
        return prefHelper.getString(STORE_URL, null)
    }


    fun saveUser(user : String) {
        prefHelper.putString(USER, user)
    }

    fun getUser(): String? {
        return prefHelper.getString(USER, null)
    }


    fun saveDeviceToken(token : String?) {
        prefHelper.putString(DEVICE_TOKEN, token?:"")
    }
    fun getDeviceToken(): String? {
        return prefHelper.getString(DEVICE_TOKEN, null)
    }

    fun saveDevicePixelId(deviceId : String?) {
        prefHelper.putString(DEVICE_PIXEL_ID, deviceId?:"")
    }
    fun getDevicePixelId(): String? {
        return prefHelper.getString(DEVICE_PIXEL_ID, null)
    }



    companion object {
        const val PIXEL_ID = "pixelId"
        const val STORE_URL = "storeUrl"
        const val USER = "user"
        const val DEVICE_TOKEN = "deviceToke"
        const val DEVICE_PIXEL_ID = "devicePixelId"

        private var sharedPreferencesUtils: SharedPreferencesUtils? = null

        fun getInstance(context: Context): SharedPreferencesUtils {
            if (sharedPreferencesUtils == null)
                sharedPreferencesUtils = SharedPreferencesUtils(PrefHelper(context.getSharedPref()))

            return sharedPreferencesUtils!!
        }
    }
}

fun Context.getSharedPref(): SharedPreferences {
    return this.getSharedPreferences("PIXEL_SDK_SHARED_PREF", Context.MODE_PRIVATE)
}
