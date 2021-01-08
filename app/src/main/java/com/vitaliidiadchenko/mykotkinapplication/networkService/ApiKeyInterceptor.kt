package com.vitaliidiadchenko.mykotkinapplication.networkService

import com.vitaliidiadchenko.mykotkinapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var original: Request = chain.request()
        val request = original.newBuilder()
            .header("api-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}