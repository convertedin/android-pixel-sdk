package convertedin.pixel.pixelsdk.utils

import android.content.Context
import convertedin.pixel.pixelsdk.data.sharedPref.SharedPreferencesUtils

class LocalDataUtils constructor(private var context: Context) {

    val sharedPreferencesUtils = SharedPreferencesUtils.getInstance(context)

    fun getString(id: Int): String {
        return context.getString(id)
    }

}
