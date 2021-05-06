package com.cavar.api_common.authenticator

import java.net.Authenticator

const val EXTRA_BEARER_TOKEN = "EXTRA_BEARER_TOKEN"
const val EXTRA_REFRESH_TOKEN = "EXTRA_REFRESH_TOKEN"
private const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"
private const val EMPTY_STRING = ""

class TokenAuthenticator(
    /*
        val baseUrl: String,
    val clientId: String,
    val clientSecret: String,
    val publisher: PublishSubject<Boolean>,
    val sharedPreferences: SharedPreferences
     */
) : Authenticator() {

    /*
      var restInterface: RestInterface? = null

    override fun authenticate(route: Route?, response: Response): Request? {
        val auth: Auth?
        if (response.request.url.toString().contains(baseUrl) ) {
            //Token refresh return second time 401 that mean refresh failed
            publisher.onNext(true)
            return null
        } else {
            auth = refreshToken()
            if (auth != null) {
                val workspace = sharedPreferences.getString(
                    EXTRA_WORKSPACES, EMPTY_STRING
                )
                saveString(EXTRA_BEARER_TOKEN, auth.accessToken)
                saveString(EXTRA_REFRESH_TOKEN, auth.refreshToken)
                return response.request.newBuilder()
                    .removeHeader(AUTHORISATION)
                    .removeHeader(X_WORKSPACE_ID)
                    .addHeader(AUTHORISATION, "$BEARER ${getString(EXTRA_BEARER_TOKEN, EMPTY_STRING)}")
                    .addHeader(X_WORKSPACE_ID, workspace!!)
                    .build()
            } else {
                publisher.onNext(true)
            }
        }
        return null
    }

    @Synchronized
    private fun refreshToken(): Auth? {
        try {
            val tokenCall = restInterface!!.refreshToken(
                baseUrl,
                getString(EXTRA_REFRESH_TOKEN, EMPTY_STRING)!!,
                GRANT_TYPE_REFRESH_TOKEN,
                clientId,
                clientSecret
            )
            return tokenCall.execute().body()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    fun getString(key: String, initValue: String) = sharedPreferences.getString(key, initValue)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
     */
}