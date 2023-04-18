package convertedin.pixel.pixelsdk.di

import android.content.Context
import convertedin.pixel.pixelsdk.utils.LocalDataUtils


object localDataModule {

    fun provideLocalDataUtils(context: Context): LocalDataUtils {
        return LocalDataUtils(context)
    }
}
