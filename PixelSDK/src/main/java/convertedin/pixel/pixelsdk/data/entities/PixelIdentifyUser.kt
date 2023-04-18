package convertedin.pixel.pixelsdk.data.entities

import com.google.gson.annotations.SerializedName


data class IdentifyRequest(
    @SerializedName("email") var email: String? = "",
    @SerializedName("csid") var csid: String? = "",
    @SerializedName("phone") var phone: String?,
    @SerializedName("country_code") var country_code: String?,
)


data class IdentifyResponse(
    @SerializedName("cid") var cid: String = "",
    @SerializedName("csid") var csid: String = ""
)