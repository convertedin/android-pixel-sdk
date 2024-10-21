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
        helper?.appOpened()
        Log.d("Pixel SDK", "$apiUrl$pixelId")
    }

    //identify user to get customer id
    @Deprecated("This function will be removed soon, use 'registerEvent', loginEvent or 'setCustomerData' instead")
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

    /**
     * This event is typically triggered when a user clicks on a push notification,
     * and should pass the received campaign id to the function, you can find the campaign id
     * in the payload of the push notification ["campaign_id"] with a string value
     *
     * @param campaignId is required
     */
    fun onPushNotificationClicked(campaignId: String) {
        helper?.clickOnPush(campaignId)
    }


    /**
     * This event is typically triggered when a user registers for the app.
     * @param email is required
     */
    fun register(
        email: String
    ) {
        helper?.register(email = email)
    }


    /**
     * This event is typically triggered when a user registers for the app.
     * @param phone is required
     */
    fun register(
        phone: String,
        countryCode: String? = null
    ) {
        helper?.register(phone = phone, countryCode = countryCode)
    }

    /**
     * This event is typically triggered when a user login into the app.
     * @param email is required
     */
    fun login(
        email: String
    ) {
        helper?.login(email = email)
    }

    /**
     * This event is typically triggered when a user login into the the app.
     * @param phone is required
     */
    fun login(
        phone: String,
        countryCode: String? = null
    ) {
        helper?.login(phone = phone, countryCode = countryCode)
    }

    /**
     * This event is typically triggered in the first screen of the app when user email is available.
     * @param email is required
     */
    fun setUserData(
        email: String
    ) {
        helper?.setUserData(email = email)
    }

    /**
     * This event is typically triggered in the first screen of the app when user phone is available.
     * @param phone is required
     */
    fun setUserData(
        phone: String,
        countryCode: String? = null
    ) {
        helper?.setUserData(phone = phone, countryCode = countryCode)
    }


    /**
     * This event is typically triggered when a user views a specific piece of content,
     * such as a product, article, or video. By tracking ViewContent events, developers can gain
     * insights into what content users are engaging with most and optimize their content strategy accordingly.
     *
     * @param currency is required
     * @param total is required
     * @param products is required, for each product `id` and `quantity` are required
     */
    fun viewContentEvent(
        currency: String,
        total: String,
        products: ArrayList<EventContent>
    ) {
        helper?.viewContentEvent(currency, total, products)
    }

    /**
     * This event is triggered when a user views a page or screen within the app.
     * By tracking PageView events, developers can gain insights into how users navigate through
     * their app and optimize the user experience accordingly.
     */
    fun pageViewEvent() {
        helper?.pageViewEvent(null, null, null)
    }

    /**
     * This event is triggered when a user adds a product to their shopping cart.
     * By tracking AddToCart events, developers can gain insights into which products are most
     * popular and optimize their product offering and pricing accordingly. The AddToCart event i
     * s often paired with other e-commerce events such as Purchase or Checkout events, and is an
     * important component of user analysis and e-commerce optimization.
     *
     * @param currency is required
     * @param total is required
     * @param products is required, for each product `id` and `quantity` are required
     */
    fun addToCartEvent(
        currency: String,
        total: String,
        products: ArrayList<EventContent>
    ) {
        if (currency.isBlank() || total.isBlank() || products.isEmpty())
            return

        helper?.addToCartEvent(currency, total, products)
    }

    /**
     * This event is triggered when a user begins the process of checking out and entering their
     * payment and shipping information. By tracking InitiateCheckout events, developers can gain
     * insights into how users interact with the checkout process and identify areas for improvement
     * to increase conversion rates. The InitiateCheckout event is often paired with other e-commerce
     * events such as AddToCart or Purchase events, and is an important component of user analysis and e-commerce optimization.
     *
     * @param currency is required
     * @param total is required
     * @param products is required, for each product `id` and `quantity` are required
     */
    fun initiateCheckoutEvent(
        currency: String,
        total: String,
        products: ArrayList<EventContent>
    ) {
        if (currency.isBlank() || total.isBlank() || products.isEmpty())
            return

        helper?.initiateCheckoutEvent(currency, total, products)
    }

    /**
     *This event is triggered when a user completes a purchase and successfully makes a payment
     * for a product or service. By tracking Purchase events, developers can gain insights into
     * which products are most popular and identify patterns in user behavior to optimize the
     * checkout process and increase sales. The Purchase event is often paired with other e-commerce
     * events such as AddToCart or InitiateCheckout events, and is an important component of
     * user analysis and e-commerce optimization.
     *
     *  @param currency is required
     *  @param total is required
     *  @param products is required, for each product `id` and `quantity` are required
     *  @param orderId is required
     */
    fun purchaseEvent(
        currency: String,
        total: String,
        products: ArrayList<EventContent>,
        orderId: String
    ) {
        if (currency.isBlank() || total.isBlank() || products.isEmpty())
            return

        helper?.purchaseEvent(currency, total, products, orderId)
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
