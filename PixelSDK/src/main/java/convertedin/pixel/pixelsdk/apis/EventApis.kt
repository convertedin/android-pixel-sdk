package convertedin.pixel.pixelsdk.apis

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
        @Body identifyRequest: IdentifyRequest
    ): Single<IdentifyResponse>

    @POST
    fun addEvent(
        @Url url: String,
        @Header("Referer") storeUrl: String,
        @Body eventRequest: EventRequest
    ): Single<Any>

    companion object {
        const val API_IDENTITY = "identity"
        const val API_EVENTS = "events"
    }

}