package com.ib.textileecommerce.module

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ib.textileecommerce.networking.NetworkRequest
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.scopes.PerApplication
import com.ib.textileecommerce.utils.Constants
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.utils.Utils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.Locale
import java.util.concurrent.TimeUnit

@Module(includes = [SessionManagerModule::class])
class NetworkModule {

    @Provides
    @PerApplication
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @PerApplication
    fun providesCacheFile(context: Context): File = File(context.cacheDir, "responses")

    @Provides
    @PerApplication
    fun providesOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(Constants.TIME_OUT, TimeUnit.MINUTES)
            .readTimeout(Constants.TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(Constants.TIME_OUT, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            //.addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(interceptor)
            .cache(cache)
            .build()

    @Provides
    @PerApplication
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    @Provides
    @PerApplication
    fun providesNetworkRequest(retrofit: Retrofit): NetworkRequest =
        retrofit.create(NetworkRequest::class.java)

    @Provides
    @PerApplication
    fun providesNetworkService(networkRequest: NetworkRequest): NetworkService =
        NetworkService(networkRequest)

    @Provides
    @PerApplication
    fun providesCache(cacheFile: File): Cache {
        lateinit var cache: Cache
        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cache
    }

    @Provides
    @PerApplication
    fun providesInterceptor(
        context: Context,
        sessionManager: SessionManager
    ): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

            val request = original.newBuilder()
                .addHeader(
                    Constants.API_KEY,
                    if (original.url.toUrl().path.contains(Constants.LOGIN) || TextUtils.isEmpty(
                            sessionManager.getAccessToken()
                        )
                    ) "" else "Bearer " + sessionManager.getAccessToken()
                )
                .addHeader(Constants.DEVICE_ID, deviceId)
                .addHeader(Constants.DEVICE_TYPE, "0")
                .addHeader(Constants.VERSION_NAME, Build.VERSION.RELEASE)
                .addHeader(Constants.TIME_ZONE, Utils.getTimeZone())
                .header("Accept", "application/json")
                .removeHeader("Pragma")
                .header(
                    "Cache-Control",
                    String.format(Locale.getDefault(), "max-age=%d", Constants.CACHE_TIME)
                )
                .build()


//            Log.e("NetworkModule","Authorization:\t  ${if (original.url().url().path.contains(Constants.REGISTER) || TextUtils.isEmpty(
//                    sessionManager.getAccessToken()
//                )
//            ) Constants.HVALUE else "Bearer " + sessionManager.getAccessToken()}")
//
//            Log.e("NetworkModule--> " , request.toString())
//            Log.e("NetworkModule-->", "Cache-Control \t ${ String.format(Locale.getDefault(), "max-age=%d", Constants.CACHE_TIME)}")

            val response = chain.proceed(request)
//            Log.e("Request URL--> " , request.url().toString())
//            Log.e("Response " , "-->".plus(response.body())
            //json response --> response.body().string();
            // Customize or return the response
            response
        }
    }
}