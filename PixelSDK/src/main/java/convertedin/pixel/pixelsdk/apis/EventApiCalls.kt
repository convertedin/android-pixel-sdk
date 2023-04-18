package convertedin.pixel.pixelsdk.apis

import convertedin.pixel.pixelsdk.ConvertedInSdk
import convertedin.pixel.pixelsdk.apis.EventApis.Companion.API_EVENTS
import convertedin.pixel.pixelsdk.apis.EventApis.Companion.API_IDENTITY
import convertedin.pixel.pixelsdk.data.entities.EventRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyResponse
import io.reactivex.Single


 class EventApiCalls(private val apis: EventApis) {

     internal fun identifyUser(
        pixelId: String,
        storeUrl: String,
        identifyRequest: IdentifyRequest
    ): Single<IdentifyResponse> =
        apis.identifyUser(
            ConvertedInSdk.apiUrl + "v1/" + pixelId + "/" + API_IDENTITY,
            storeUrl,
            identifyRequest = identifyRequest
        )

     internal fun addEvent(
        pixelId: String,
        storeUrl: String,
        eventRequest: EventRequest
    ): Single<Any> =
        apis.addEvent(
            ConvertedInSdk.apiUrl + "v1/" + pixelId + "/" + API_EVENTS,
            storeUrl,
            eventRequest = eventRequest
        )
}