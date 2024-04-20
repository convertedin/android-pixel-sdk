package convertedin.pixel.pixelsdk.viewmodel


import convertedin.pixel.pixelsdk.data.entities.EventRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyResponse
import convertedin.pixel.pixelsdk.repository.EventsRepository
import io.reactivex.functions.Consumer
import java.util.UUID

class EventsViewModel : BaseViewModel() {

    private lateinit var repository: EventsRepository

    fun setRepository(eventsRepository: EventsRepository) {
        this.repository = eventsRepository
    }

    fun savePixelId(pixelId: String) {
        repository.savePixelId(pixelId)
    }

    fun getPixelId(): String? {
        return repository.getPixelId()
    }


    fun saveStoreUrl(storeUrl: String) {
        repository.saveStoreUrl(storeUrl)
    }

    fun getStoreUrl(): String? {
        return repository.getStoreUrl()
    }


    fun saveDeviceId() {
        repository.saveDevicePixelId(UUID.randomUUID().toString())
    }

    fun getDeviceId(): String? {
        return repository.getDevicePixelId()
    }


    fun getUser(): IdentifyResponse? {
        return repository.getUser()
    }

    fun identifyUser(identifyRequest: IdentifyRequest) {
        val storeUrl = repository.getStoreUrl()
        val pixelId = repository.getPixelId()

        if (storeUrl != null && pixelId != null)
            subscribe(
                repository.identifyUser(pixelId, storeUrl, identifyRequest = identifyRequest)
                    .doAfterSuccess {
                        repository.saveUser(it)
                    },
                Consumer {}
            )
    }

    fun addEvent(eventRequest: EventRequest) {
        val storeUrl = repository.getStoreUrl()
        val pixelId = repository.getPixelId()

        if (storeUrl != null && pixelId != null)
            subscribe(
                repository.addEvent(pixelId, storeUrl, eventRequest = eventRequest),
                Consumer {}
            )
    }


}