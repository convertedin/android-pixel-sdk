package convertedin.pixel.pixelsdk.data.entities

import com.google.gson.annotations.SerializedName


data class EventRequest(
    @SerializedName("event") var event: String,
    @SerializedName("cuid") var cuid: String?,
    @SerializedName("data") var data: EventData?,
    @SerializedName("csid") var csid: String? = null,
    @SerializedName("cid") var cid: String?,
    @SerializedName("ca") var campaignId: String?,
    @SerializedName("order_id") var orderId: String? = null,
)

data class EventData(
    @SerializedName("currency") var currency: String?,
    @SerializedName("value") var value: String?,
    @SerializedName("content") var content: ArrayList<EventContent>?,
)

data class EventContent(
    @SerializedName("id") var id: String,
    @SerializedName("quantity") var quantity: String,
    @SerializedName("name") var name: String?,
    @SerializedName("category") var category: String? = null
)