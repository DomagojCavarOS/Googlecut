package com.domba.googlecut.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cavar.papercut.constants.EMPTY_STRING
import com.cavar.papercut.constants.INIT_LONG
import com.cavar.papercut.live_data.SingleLiveEvent
import com.domba.*
import com.domba.googlecut.R
import com.domba.models.Hexa
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

const val ERROR_CODE_BAD_REQUEST = 400
const val ERROR_UNATHORISED = 401
const val ERROR_FORBIDDEN = 403
const val ERROR_NOT_FOUND = 404
const val EXTRA_BEARER_TOKEN = "EXTRA_BEARER_TOKEN"
const val DELAY_TOAST_SHORT = 2000L
const val DELAY_TOAST_LONG = 3500L

abstract class BaseErrorViewModel : BaseLoaderViewModel(), ErrorUiHandler, RefreshListener,
    KoinComponent {
    var snackErrorTimeStamp = INIT_LONG

    val errorHandler: ErrorHandler by inject()

    private val _showError = SingleLiveEvent<SnackData>()
    val showError: LiveData<SnackData>
        get() = _showError

    var showErrorLayout = MutableLiveData<ErrorData>()
    var showErrorLayoutChild = MutableLiveData<ErrorData>()

    override fun handleError(
        error: ErrorPapercut,
        throwable: Throwable?,
        handlingFunction: (throwable: Throwable?) -> Hexa<Int, Any, Int, Int, Int, String>,
        errorFilter: ErrorFilter?,
        useChildErrorLayout: Boolean,
        showLogoutPopUp: Boolean,
        skipErrorShowing: Boolean
    ) {
        throwable?.let {
            val handledError = errorHandler.handleError(throwable, handlingFunction)
            filterError(errorFilter, handledError)
            if (!skipErrorShowing)
                when (error) {
                    is ErrorLayout -> {
                        val errorData = if (useChildErrorLayout)
                            showErrorLayoutChild
                        else
                            showErrorLayout
                        errorData.value = ErrorData(
                            true,
                            handledError.first,
                            handledError.second,
                            handledError.third,
                            handledError.fourth,
                            this
                        )
                    }
                    is ErrorSnack -> {
                        if (snackErrorTimeStamp + error.delay < System.currentTimeMillis()) {
                            snackErrorTimeStamp = System.currentTimeMillis()
                            _showSnack.value = SnackData(handledError.second, R.color.red_brick)
                        }
                    }
                }
        }
    }

    fun hideErrorLayout() {
        showErrorLayout.value = showErrorLayout.value?.apply { hasError = false }
    }

    fun hideChildErrorLayout() {
        showErrorLayoutChild.value = showErrorLayoutChild.value?.apply { hasError = false }
    }

    fun getErrorCode(throwable: Throwable) = errorCode(throwable)

    fun getErrorObj(throwable: Throwable) = errorCodeCustom(throwable)

    fun getParsedError(json: String): Any {
        return getErrorMessage(json)
    }
}

private fun filterError(
    errorFilter: ErrorFilter?,
    handledError: Hexa<Int, Any, Int, Int, Int, String>
) {
    errorFilter?.let {
        if (it.errorCode == handledError.fifth)
            it.listener.specificErrorFilter(
                handledError.fifth,
                handledError.second as String,
                handledError.sixt
            )
    }
}

sealed class ErrorPapercut
data class ErrorSnack(val delay: Long = INIT_LONG) : ErrorPapercut()
object ErrorLayout : ErrorPapercut()

data class ErrorData(
    var hasError: Boolean,
    val title: Int,
    val description: Any,
    val buttonText: Int,
    val imageSrc: Int,
    val listener: RefreshListener,
    val errorField: ArrayList<Pair<String, String>>? = null
): State()

interface ErrorUiHandler {
    fun handleError(
        error: ErrorPapercut,
        throwable: Throwable?,
        handlingFunction: (throwable: Throwable?) -> Hexa<Int, Any, Int, Int, Int, String> = {
            baseErrorHandler(throwable)
        },
        errorFilter: ErrorFilter? = null,
        useChildErrorLayout: Boolean = false,
        showLogoutPopUp: Boolean = false,
        skipErrorShowing: Boolean = false
    )
}

interface RefreshListener {
    fun onRefresh()
}

data class ErrorFilter(val listener: ErrorFilterListener, val errorCode: Int)
interface ErrorFilterListener {
    fun specificErrorFilter(errorCode: Int, errorMessage: String, field: String = EMPTY_STRING)
}