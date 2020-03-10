package com.example.marveldex.api

import com.example.marveldex.BuildConfig
import com.example.marveldex.extensions.toMD5
import okhttp3.Interceptor
import okhttp3.Response

class MarvelApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentTimestamp = System.currentTimeMillis()
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("ts", currentTimestamp.toString())
            .addQueryParameter("apikey", BuildConfig.PRIVATE_KEY)
            .addQueryParameter(
                "hash",
                (currentTimestamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).toMD5()
            )
            .build()
        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}