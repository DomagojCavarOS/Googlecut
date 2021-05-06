package com.domba.googlecut.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domba.googlecut.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

abstract class BaseLoaderViewModel : BaseEventViewModel(), BaseLoader {

    protected val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isEmptyState = MutableLiveData<EmptyStateData>()

    override fun isLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun hideEmptyState() {
        isEmptyState.value = isEmptyState.value?.apply { isEmpty = false }
    }

    fun <T> Flow<T>.withLoader(): Flow<T> {
        return catch {
            isLoading(false)
        }
            .onEach { isLoading(false) }
    }
}

interface BaseLoader {
    fun isLoading(isLoading: Boolean)
}

data class EmptyStateData(
    var isEmpty: Boolean = false,
    val title: Any? = R.string.empty_list_title,
    val description: Any? = R.string.empty_description,
    val payload: Any? = null
) : State()