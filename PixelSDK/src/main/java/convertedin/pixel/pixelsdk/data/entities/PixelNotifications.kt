package convertedin.pixel.pixelsdk.data.entities

import com.google.gson.annotations.SerializedName


data class SaveTokenRequest(
    @SerializedName("customer_id") var customerId: String,
    @SerializedName("device_token") var deviceToken: String?,
    @SerializedName("token_type") var tokenType: String?
)

data class DeleteTokenRequest(
    @SerializedName("device_token") var deviceToken: String?,
    @SerializedName("token_type") var tokenType: String?
)

data class RefreshTokenRequest(
    @SerializedName("device_token") var deviceToken: String?,
    @SerializedName("token_type") var tokenType: String?,
    @SerializedName("new_device_token") var newDeviceToken: String?,
    @SerializedName("new_token_type") var newTokenType: String?
)
