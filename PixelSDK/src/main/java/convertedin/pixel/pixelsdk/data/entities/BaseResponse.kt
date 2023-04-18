package convertedin.pixel.pixelsdk.data.entities
import com.google.gson.annotations.SerializedName

internal class BaseResponse<T> {
    @field:SerializedName("code")
    val code: Int? = null

    @field:SerializedName("message")
    val message: String? = ""

    @field:SerializedName("data")
    val data: T? = null
}
