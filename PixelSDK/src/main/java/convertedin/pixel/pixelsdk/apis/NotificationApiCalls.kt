package convertedin.pixel.pixelsdk.apis

import convertedin.pixel.pixelsdk.ConvertedInSdk
import convertedin.pixel.pixelsdk.apis.NotificationApis.Companion.API_DELETE_DEVICE_TOKEN
import convertedin.pixel.pixelsdk.apis.NotificationApis.Companion.API_REFRESH_DEVICE_TOKEN
import convertedin.pixel.pixelsdk.apis.NotificationApis.Companion.API_SAVE_DEVICE_TOKEN
import convertedin.pixel.pixelsdk.data.entities.DeleteTokenRequest
import convertedin.pixel.pixelsdk.data.entities.RefreshTokenRequest
import convertedin.pixel.pixelsdk.data.entities.SaveTokenRequest
import io.reactivex.Single

class NotificationApiCalls(private val apis: NotificationApis) {
    
    fun saveDeviceToken(
        pixelId: String,
        saveTokenRequest: SaveTokenRequest
    ): Single<Any> =
        apis.saveDeviceToken(
            ConvertedInSdk.apiUrl + "webhooks/push-notification/" + pixelId + "/" + API_SAVE_DEVICE_TOKEN,
            saveTokenRequest
        )


    fun deleteDeviceToken(
        pixelId: String,
        deleteTokenRequest: DeleteTokenRequest
    ): Single<Any> =
        apis.deleteDeviceToken(
            ConvertedInSdk.apiUrl + "webhooks/push-notification/" + pixelId + "/" + API_DELETE_DEVICE_TOKEN,
            deleteTokenRequest
        )


    fun refreshDeviceToken(
        pixelId: String,
        refreshTokenRequest: RefreshTokenRequest
    ): Single<Any> =
        apis.refreshDeviceToken(
            ConvertedInSdk.apiUrl + "webhooks/push-notification/" + pixelId + "/" + API_REFRESH_DEVICE_TOKEN,
            refreshTokenRequest
        )


}