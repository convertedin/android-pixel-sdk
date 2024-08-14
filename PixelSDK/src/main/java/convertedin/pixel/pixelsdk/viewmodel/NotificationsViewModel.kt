package convertedin.pixel.pixelsdk.viewmodel

import convertedin.pixel.pixelsdk.data.entities.DeleteTokenRequest
import convertedin.pixel.pixelsdk.data.entities.RefreshTokenRequest
import convertedin.pixel.pixelsdk.data.entities.SaveTokenRequest
import convertedin.pixel.pixelsdk.repository.NotificationsRepository


 class NotificationsViewModel : BaseViewModel() {

     private lateinit var repository: NotificationsRepository

     fun setRepository(notificationsRepository: NotificationsRepository) {
         this.repository = notificationsRepository
     }

    fun getDeviceToken() : String? {
        return repository.getDeviceToken()
    }

    fun saveDeviceToken(saveTokenRequest: SaveTokenRequest) {
        val pixelId = repository.getPixelId()

        if (pixelId != null)
            subscribe(
                repository.saveDeviceToken(pixelId, saveTokenRequest = saveTokenRequest)
                    .doAfterSuccess {
                        repository.saveDeviceTokenLocal(saveTokenRequest.deviceToken)
                    }, {}
            )
    }

    fun deleteDeviceToken(deleteTokenRequest: DeleteTokenRequest) {
        val pixelId = repository.getPixelId()

        if (pixelId != null)
            subscribe(
                repository.deleteDeviceToken(pixelId, deleteTokenRequest = deleteTokenRequest)
                    .doAfterSuccess {
//                        repository.saveDeviceTokenLocal(null) //TODO temporary 13/08/2023
                    }, {}
            )
    }

    fun refreshDeviceToken(refreshTokenRequest: RefreshTokenRequest) {
        val pixelId = repository.getPixelId()

        if (pixelId != null)
            subscribe(
                repository.refreshDeviceToken(pixelId, refreshTokenRequest = refreshTokenRequest)
                    .doAfterSuccess {
                        repository.saveDeviceTokenLocal(refreshTokenRequest.newDeviceToken)
                    }, {}
            )
    }


 }