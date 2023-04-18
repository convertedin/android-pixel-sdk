package convertedin.pixel.pixelsdk.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import convertedin.pixel.pixelsdk.BuildConfig
import convertedin.pixel.pixelsdk.ConvertedInSdk
import convertedin.pixel.pixelsdk.apis.EventApiCalls
import convertedin.pixel.pixelsdk.apis.EventApis
import convertedin.pixel.pixelsdk.apis.NotificationApiCalls
import convertedin.pixel.pixelsdk.apis.NotificationApis
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private val gson: Gson = GsonBuilder().setLenient().create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ConvertedInSdk.apiUrl + "v1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(
            OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
                .addInterceptor {
                    val request = it.request().newBuilder()
                    request.addHeader("accept", "application/json")
                    it.proceed(request.build())
                }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        )
        .build()


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY
        } else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    fun provideEventApiCalls(): EventApiCalls {
        return EventApiCalls(retrofit.create(EventApis::class.java))
    }

    fun provideNotificationApiCalls(): NotificationApiCalls {
        return NotificationApiCalls(retrofit.create(NotificationApis::class.java))
    }

}
