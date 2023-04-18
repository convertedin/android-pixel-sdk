package convertedin.pixel.pixelsdk.repository

import convertedin.pixel.pixelsdk.utils.LocalDataUtils


abstract class BaseRepository(private val localDataUtils: LocalDataUtils)  {

    fun savePixelId(pixelId: String) = localDataUtils.sharedPreferencesUtils.savePixelId(pixelId)
    fun getPixelId() = localDataUtils.sharedPreferencesUtils.getPixelId()

    fun saveStoreUrl(storeUrl: String) =
        localDataUtils.sharedPreferencesUtils.saveStoreUrl(storeUrl)
    fun getStoreUrl() = localDataUtils.sharedPreferencesUtils.getStoreUrl()


    fun saveDevicePixelId(deviceId: String) = localDataUtils.sharedPreferencesUtils.saveDevicePixelId(deviceId)
    fun getDevicePixelId() = localDataUtils.sharedPreferencesUtils.getDevicePixelId()

}