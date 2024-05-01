package convertedin.pixel.pixelsdk.utils

import android.content.Context
import android.util.Log
import convertedin.pixel.pixelsdk.data.entities.*
import convertedin.pixel.pixelsdk.data.entities.EventRequest
import convertedin.pixel.pixelsdk.data.entities.IdentifyRequest
import convertedin.pixel.pixelsdk.data.entities.SaveTokenRequest
import convertedin.pixel.pixelsdk.di.NetworkModule
import convertedin.pixel.pixelsdk.di.localDataModule
import convertedin.pixel.pixelsdk.repository.EventsRepository
import convertedin.pixel.pixelsdk.repository.NotificationsRepository
import convertedin.pixel.pixelsdk.viewmodel.EventsViewModel
import convertedin.pixel.pixelsdk.viewmodel.NotificationsViewModel


class PixelHelper(context: Context) {

    private var eventsViewModel: EventsViewModel
    private var notificationsViewModel: NotificationsViewModel

    init {
        val localDataUtils = localDataModule.provideLocalDataUtils(context)
        val eventsService = NetworkModule.provideEventApiCalls()
        val notificationsService = NetworkModule.provideNotificationApiCalls()

        val eventsRepository = EventsRepository(eventsService, localDataUtils)
        val notificationsRepository = NotificationsRepository(notificationsService, localDataUtils)


        eventsViewModel = EventsViewModel()
        eventsViewModel.setRepository(eventsRepository)

        notificationsViewModel = NotificationsViewModel()
        notificationsViewModel.setRepository(notificationsRepository)
    }

    internal fun saveData(pixelId: String, storeUrl: String) {
        eventsViewModel.savePixelId(pixelId)
        eventsViewModel.saveStoreUrl(storeUrl)
    }

    internal fun saveDeviceId() {
        if (eventsViewModel.getDeviceId().isNullOrEmpty()) {
            eventsViewModel.saveDeviceId()
        }
    }

    internal fun identifyUser(email: String?, phone: String?, countryCode: String?) {
        val csid =
            if (email.isNullOrBlank() && phone.isNullOrBlank() && countryCode.isNullOrBlank()) {
                eventsViewModel.getUser()?.csid
            } else {
                eventsViewModel.getUser()?.cid
            }

        if (validUrls())
            eventsViewModel.identifyUser(
                IdentifyRequest(
                    email = email,
                    phone = phone,
                    countryCode = countryCode,
                    csid = csid,
                    src = "push"
                )
            )

        saveDeviceToken(deviceToken = notificationsViewModel.getDeviceToken())
    }

    internal fun identifyUser() {
        val csid = if (eventsViewModel.getUser()?.csid.isNullOrBlank()) {
            null
        } else {
            eventsViewModel.getUser()?.csid
        }

        if (validUrls())
            eventsViewModel.identifyUser(
                IdentifyRequest(src = "push", csid = csid)
            )
    }

    internal fun addEvent(
        eventName: String,
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent(eventName, currency, total, products)
    }

    internal fun viewContentEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent("ViewContent", currency, total, products)
    }

    internal fun pageViewEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent("PageView", currency, total, products)
    }

    internal fun addToCartEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent("AddToCart", currency, total, products)
    }

    internal fun initiateCheckoutEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent("InitiateCheckout", currency, total, products)
    }

    internal fun purchaseEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        if (validUrls())
            sendEvent("Purchase", currency, total, products)
    }


    private fun sendEvent(
        eventName: String,
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        eventsViewModel.addEvent(
            EventRequest(
                event = eventName,
                cuid = eventsViewModel.getDeviceId(),
                data = EventData(currency = currency, value = total, content = products),
                csid = eventsViewModel.getUser()?.cid
            )
        )
    }


    /////////////////////////////////////////////////////////////////////

    internal fun saveDeviceToken(deviceToken: String?) {
        if (validUrls())
            eventsViewModel.getUser()?.cid?.let {
                notificationsViewModel.saveDeviceToken(
                    SaveTokenRequest(
                        customerId = it,
                        deviceToken = deviceToken,
                        tokenType = "android"
                    )
                )
            }
    }


    internal fun deleteDeviceToken() {
        if (validUrls())
            eventsViewModel.getUser()?.cid?.let {
                notificationsViewModel.deleteDeviceToken(
                    DeleteTokenRequest(
                        deviceToken = notificationsViewModel.getDeviceToken(),
                        tokenType = "android"
                    )
                )
            }
    }

    private fun refreshDeviceToken(deviceToken: String?) {
        notificationsViewModel.refreshDeviceToken(
            RefreshTokenRequest(
                deviceToken = notificationsViewModel.getDeviceToken(),
                tokenType = "android",
                newDeviceToken = deviceToken,
                newTokenType = "android"
            )
        )

    }


    private fun validUrls(): Boolean {
        val valid = !eventsViewModel.getPixelId().isNullOrEmpty() && !eventsViewModel.getDeviceId()
            .isNullOrEmpty()
        if (!valid)
            Log.e("pixel sdk error", "error, provide store url and pixel id")

        return valid
    }


}
