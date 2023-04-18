package convertedin.pixel.pixelsdk.repository

import convertedin.pixel.pixelsdk.apis.NotificationApiCalls
import convertedin.pixel.pixelsdk.data.entities.DeleteTokenRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyResponse
import convertedin.pixel.pixelsdk.data.entities.RefreshTokenRequest
import convertedin.pixel.pixelsdk.data.entities.SaveTokenRequest
import convertedin.pixel.pixelsdk.data.sharedPref.toObjectFromJson
import convertedin.pixel.pixelsdk.utils.LocalDataUtils


class NotificationsRepository(private val apiCalls: NotificationApiCalls,
                                       private val localDataUtils: LocalDataUtils) : BaseRepository(localDataUtils) {

    fun saveDeviceTokenLocal(token: String?) =
        localDataUtils.sharedPreferencesUtils.saveDeviceToken(token)

    fun getDeviceToken() =
        localDataUtils.sharedPreferencesUtils.getDeviceToken()

    fun getUser(): IdentifyResponse? = localDataUtils.sharedPreferencesUtils.getUser()
        ?.toObjectFromJson<IdentifyResponse>(IdentifyResponse::class.java)

    fun saveDeviceToken(pixelId: String, saveTokenRequest: SaveTokenRequest) =
        apiCalls.saveDeviceToken(pixelId, saveTokenRequest = saveTokenRequest)

    fun deleteDeviceToken(pixelId: String, deleteTokenRequest: DeleteTokenRequest) =
        apiCalls.deleteDeviceToken(pixelId, deleteTokenRequest = deleteTokenRequest)

    fun refreshDeviceToken(pixelId: String, refreshTokenRequest: RefreshTokenRequest) =
        apiCalls.refreshDeviceToken(pixelId, refreshTokenRequest = refreshTokenRequest)


}