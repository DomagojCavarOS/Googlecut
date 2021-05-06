package com.domba.api.services

import com.domba.api.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL_PROD = "https://rest-api-dev-01.analyticom.de/v2/api/"
const val BASE_URL_DEV = "https://rest-api-dev-01.analyticom.de/v2/api/"
const val BASE_URL_STAGE = "https://rest-api-dev-01.analyticom.de/v2/api/"

//"https://comet-play-demo-backend.analyticom.de/"
//https://comet-play-test-backend.analyticom.de/
const val AUTHORISATION = "Authorization"
const val BEARER = "Bearer"
const val DEFAULT = "Croatia"
const val EXTRA_COUNTRY = "EXTRA_WORKSPACES"
const val BASE_URL = "https://base_url/"
const val ACCEPT_LANGUAGE = "Accept-Language"
const val EXTRA_WORKSPACES = "EXTRA_WORKSPACES"
const val X_WORKSPACE_ID = "X-Workspace-ID"
const val CONTENT_TYPE = "Content-Type"
const val URL_ENCODED = "application/x-www-form-urlencoded"
const val EXTRA_BEARER_TOKEN = "EXTRA_BEARER_TOKEN"
const val LOGIN_HOST = "sso-demo.analyticom.de"
const val URL_SIGN_UP = "api/public/signup"
const val URL_FORGOT_PASSWORD = "api/accounts/security/requestPasswordReset"

const val FLAVOR_PRODUCTION = "production"
const val FLAVOR_DEV = "dev"
const val FLAVOR_STAGE = "stage"

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return httpLoggingInterceptor
}

fun provideLocalisationHeader(): Interceptor {
    return invoke {
        val request = it.request()
            .newBuilder()
            .addHeader(ACCEPT_LANGUAGE, Locale.getDefault().toString())
            .build()
        it.proceed(request)
    }
}

fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(provideLoggingInterceptor())
    .addInterceptor(provideLocalisationHeader())
    .build()

fun provideGson() = GsonBuilder().create()

fun provideConverterFactory() = GsonConverterFactory.create(provideGson())

inline fun <reified T> createRestService(baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkHttpClient())
        .addConverterFactory(provideConverterFactory())
        .build()
    return retrofit.create(T::class.java)
}



