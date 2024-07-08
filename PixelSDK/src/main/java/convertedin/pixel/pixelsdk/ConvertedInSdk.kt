package convertedin.pixel.pixelsdk

import android.content.Context
import android.util.Log
import convertedin.pixel.pixelsdk.data.entities.EventContent
import convertedin.pixel.pixelsdk.utils.PixelHelper

class ConvertedInSdk {


    companion object {
        var apiUrl: String = "https://app.converted.in/api/"
        private var sInstance: ConvertedInSdk? = null
        private var helper: PixelHelper? = null

        private fun getInstance(context: Context): ConvertedInSdk {
            if (sInstance == null) {
                sInstance = ConvertedInSdk()
                if (helper == null)
                    helper = PixelHelper(context)

            }
            return sInstance!!
        }

    }

    //initialize SDK, pixel id and store url
    //u can obtain these data from converted in dashboard
    fun initialize(context: Context, pixelId: String, storeUrl: String) {
        getInstance(context)
        helper?.saveData(pixelId, storeUrl)
        helper?.saveDeviceId()

        helper?.identifyUser()
        Log.d("Pixel SDK", "$apiUrl$pixelId")
    }

    //identify user to get customer id
    fun identifyUser(
        email: String? = null,
        phone: String? = null,
        countryCode: String? = null
    ) {
        helper?.identifyUser(email = email, phone = phone, countryCode = countryCode)
    }


    // add any event
    fun addEvent(
        eventName: String,
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.addEvent(eventName, currency, total, products)
    }

    fun registerEvent() {
        helper?.registerEvent()
    }

    // add view content event
    fun viewContentEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.viewContentEvent(currency, total, products)
    }

    // add view page event
    fun pageViewEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.pageViewEvent(currency, total, products)
    }

    // add to cart event
    fun addToCartEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.addToCartEvent(currency, total, products)
    }

    // add init checkout event
    fun initiateCheckoutEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.initiateCheckoutEvent(currency, total, products)
    }

    // add purchase event
    fun purchaseEvent(
        currency: String?,
        total: String?,
        products: ArrayList<EventContent>?
    ) {
        helper?.purchaseEvent(currency, total, products)
    }

    // add device id
    fun saveDeviceToken(token: String) {
        helper?.saveDeviceToken(token)
    }

    // delete token
    fun deleteDeviceToken() {
        helper?.deleteDeviceToken()
    }


}
