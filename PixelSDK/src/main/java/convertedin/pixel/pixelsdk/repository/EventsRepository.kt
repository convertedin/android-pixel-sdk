package convertedin.pixel.pixelsdk.repository

import convertedin.pixel.pixelsdk.apis.EventApiCalls
import convertedin.pixel.pixelsdk.data.entities.EventRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyResponse
import convertedin.pixel.pixelsdk.data.sharedPref.toJsonString
import convertedin.pixel.pixelsdk.data.sharedPref.toObjectFromJson
import convertedin.pixel.pixelsdk.utils.LocalDataUtils


class EventsRepository(
    private val apiCalls: EventApiCalls,
    private val localDataUtils: LocalDataUtils
) : BaseRepository(localDataUtils) {

    fun saveUser(user: IdentifyResponse) =
        localDataUtils.sharedPreferencesUtils.saveUser(user.toJsonString())

    fun getUser(): IdentifyResponse? = localDataUtils.sharedPreferencesUtils.getUser()
        ?.toObjectFromJson<IdentifyResponse>(IdentifyResponse::class.java)

    fun saveCampaignId(campaignId: String?) =
        localDataUtils.sharedPreferencesUtils.saveCampaignId(campaignId)

    fun getCampaignId() = localDataUtils.sharedPreferencesUtils.getCampaignId()

    fun identifyUser(pixelId: String, storeUrl: String, identifyRequest: IdentifyRequest) =
        apiCalls.identifyUser(pixelId, storeUrl, identifyRequest = identifyRequest)

    fun addEvent(pixelId: String, storeUrl: String, eventRequest: EventRequest) =
        apiCalls.addEvent(pixelId, storeUrl, eventRequest = eventRequest)
}