package convertedin.pixel.pixelsdk.apis

import convertedin.pixel.pixelsdk.BuildConfig
import convertedin.pixel.pixelsdk.data.entities.EventRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url


interface EventApis {

    @POST
    fun identifyUser(
        @Url url: String,
        @Header("Referer") storeUrl: String,
        @Header("X-SDK-Version") sdkVersion: String = BuildConfig.SDK_VERSION,
        @Header("X-Server-SDK-Version") serverVersion : String = "2.0",
        @Header("X-Platform") platform: String = "android",
        @Body identifyRequest: IdentifyRequest
    ): Single<IdentifyResponse>

    @POST
    fun addEvent(
        @Url url: String,
        @Header("Referer") storeUrl: String,
        @Header("X-SDK-Version") sdkVersion: String = BuildConfig.SDK_VERSION,
        @Header("X-Server-SDK-Version") serverVersion : String = "2.0",
        @Header("X-Platform") platform: String = "android",
        @Body eventRequest: EventRequest
    ): Single<Any>

    companion object {
        const val API_IDENTITY = "identity"
        const val API_EVENTS = "events"
    }

}