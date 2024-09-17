package convertedin.pixel.pixelsdk.data.entities

import com.google.gson.annotations.SerializedName


data class IdentifyRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("csid") var csid: String? = null,
    @SerializedName("anonymous_cid") var anonymousCid: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("country_code") var countryCode: String? = null,
    @SerializedName("src") var src: String? = null,
    @SerializedName("cuid") var cuid: String?,
)


data class IdentifyResponse(
    @SerializedName("cid") var cid: String = "",
    @SerializedName("csid") var csid: String = ""
)