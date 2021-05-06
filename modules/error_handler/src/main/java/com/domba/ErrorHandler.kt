package com.domba

import com.domba.error_handler.R
import com.domba.error_handler.compound_view.INIT_INT
import com.domba.models.HttpErrorModel
import com.domba.models.Hexa
import com.google.gson.Gson
import retrofit2.adapter.rxjava3.HttpException
import java.io.IOException

private const val ERROR_CODE_UNKNOWN = 9999
private const val ERROR_CODE_START_GROUP = 400
private const val ERROR_CODE_END_GROUP = 511
const val ERROR_CODE_BAD_REQUEST = 400
const val ERROR_CODE_FORBIDDEN = 403
const val EMPTY_STRING = ""

class ErrorHandler {

    fun handleError(
        throwable: Throwable?,
        handlingFunction: (throwable: Throwable?) -> Hexa<Int, Any, Int, Int, Int, String>
    ): Hexa<Int, Any, Int, Int, Int, String> {
        return handlingFunction(throwable)
    }
}

fun baseErrorHandler(throwable: Throwable?): Hexa<Int, Any, Int, Int, Int, String> {
    if (throwable != null) {
        try {
            when (throwable) {
                is HttpException -> {
                    //Server error
                    val json = throwable.response()!!.errorBody()!!.string()
                    val httpErrorModel = Gson().fromJson<HttpErrorModel>(json, HttpErrorModel::class.java)
                    if(httpErrorModel.errorDescription != null) {
                        return Hexa(
                            R.string.error_server_title, httpErrorModel.errorDescription,
                            R.string.try_again, R.drawable.ic_error_server, throwable.code(), EMPTY_STRING
                        )
                    }
                    if (httpErrorModel?.apierror != null && httpErrorModel.apierror.message.isNotEmpty()) {
                        val error = httpErrorModel.apierror.message
                        return Hexa(
                            R.string.error_server_title, error,
                            R.string.try_again, R.drawable.ic_error_server, throwable.code(),
                            httpErrorModel.apierror.field ?: EMPTY_STRING
                        )
                    }
                    if (httpErrorModel.messages != null && httpErrorModel.messages.isNotEmpty()) {
                        val error = httpErrorModel.messages[0].message
                        return Hexa(
                            R.string.error_server_title, error,
                            R.string.try_again, R.drawable.ic_error_server, throwable.code(), EMPTY_STRING
                        )
                    }
                    return Hexa(
                        R.string.error_server_title, R.string.error_server_description,
                        R.string.try_again, R.drawable.ic_error_server,  ERROR_CODE_UNKNOWN, EMPTY_STRING
                    )
                }
                is IOException -> {
                    //network error
                    return Hexa(
                        R.string.error_internet_title, R.string.error_internet_description,
                        R.string.try_again, R.drawable.ic_error_network, ERROR_CODE_UNKNOWN, EMPTY_STRING
                    )
                }
                else -> {
                    //some other error
                    return Hexa(
                        R.string.error_unexpected_error_title, R.string.error_unexpected_error_description,
                        R.string.try_again, R.drawable.ic_error_server, ERROR_CODE_UNKNOWN, EMPTY_STRING
                    )
                }
            }
        } catch (e: Exception) {
            //handle error by error code
            e.printStackTrace()
            if (throwable is HttpException) {
                when (throwable.code()) {
                    in ERROR_CODE_START_GROUP..ERROR_CODE_END_GROUP -> return Hexa(
                        R.string.error_server_title, R.string.error_server_description,
                        R.string.try_again, R.drawable.ic_error_server, ERROR_CODE_UNKNOWN, EMPTY_STRING
                    )
                    else -> return Hexa(
                        R.string.error_internet_title, R.string.error_internet_description,
                        R.string.try_again, R.drawable.ic_error_network, ERROR_CODE_UNKNOWN, EMPTY_STRING
                    )
                }
            } else
                return Hexa(
                    R.string.error_unexpected_error_title, R.string.error_unexpected_error_description,
                    R.string.try_again, R.drawable.ic_error_server, ERROR_CODE_UNKNOWN, EMPTY_STRING
                )
        }
    } else {
        //some other error
        return Hexa(
            R.string.error_unexpected_error_title, R.string.error_unexpected_error_description,
            R.string.try_again, R.drawable.ic_error_server, ERROR_CODE_UNKNOWN, EMPTY_STRING
        )
    }
}

fun errorCode(throwable: Throwable): Int {
    return if (throwable is HttpException)
        throwable.code()
    else
        INIT_INT
}

fun errorCodeCustom(throwable: Throwable): Pair<Int, String> {
    return if (throwable is HttpException) {
        val json = throwable.response()!!.errorBody()!!.string()
        val httpErrorModel = Gson().fromJson<HttpErrorModel>(json, HttpErrorModel::class.java)
        if (httpErrorModel.messages != null && httpErrorModel.messages.isNotEmpty())
            Pair(
                httpErrorModel.messages[0].type,
                httpErrorModel.messages[0].additionalInfo?.oldDeviceId ?: EMPTY_STRING
            )
        else
            Pair(INIT_INT, EMPTY_STRING)
    } else
        Pair(INIT_INT, EMPTY_STRING)
}

fun getErrorMessage(json: String): Any {
    try {
        val httpErrorModel: HttpErrorModel? =
            Gson().fromJson<HttpErrorModel>(json, HttpErrorModel::class.java)
        return httpErrorModel!!.apierror!!.message
    } catch (e: Exception) {
        return R.string.error_unexpected_error_description
    }
}