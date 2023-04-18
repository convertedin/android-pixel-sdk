package convertedin.pixel.pixelsdk.apis

import convertedin.pixel.pixelsdk.data.entities.DeleteTokenRequest
import convertedin.pixel.pixelsdk.data.entities.RefreshTokenRequest
import convertedin.pixel.pixelsdk.data.entities.SaveTokenRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url


 interface NotificationApis {

    @POST
    fun saveDeviceToken(@Url url: String, @Body saveTokenRequest: SaveTokenRequest): Single<Any>

    @POST
    fun deleteDeviceToken(
        @Url url: String,
        @Body deleteTokenRequest: DeleteTokenRequest
    ): Single<Any>

    @POST
    fun refreshDeviceToken(
        @Url url: String,
        @Body refreshTokenRequest: RefreshTokenRequest
    ): Single<Any>


    companion object {
        const val API_SAVE_DEVICE_TOKEN = "deviceTokens/save"
        const val API_DELETE_DEVICE_TOKEN = "deviceTokens/delete"
        const val API_REFRESH_DEVICE_TOKEN = "deviceTokens/refresh"
    }

}