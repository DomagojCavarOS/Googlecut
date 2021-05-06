package com.domba.googlecut.view_model

import androidx.lifecycle.viewModelScope
import com.domba.baseErrorHandler
import com.domba.models.Hexa
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class GoogleViewModel : BaseErrorViewModel() {

    private val flowList = ArrayList<Flow<State>>()
    abstract fun addFlow(list: ArrayList<Flow<State>>): ArrayList<Flow<State>>
    var isLive = false
    var vmScope = viewModelScope

    @FlowPreview
    fun initDisposable() {
        isLive = true
        vmScope.launch {
            addFlow(flowList)
                .asFlow()
                .flattenMerge()
                .collect { subscribeFilter(it) }
        }
    }

    fun subscribeFilter(state: State) {
        if (!state.skipLoaderRemoval)
            _isLoading.value = false
        when (state) {
            is DataList -> {
                state.list.forEach { subscribeFilter(it) }
            }
            is OnError -> {
                handleError(
                    state.error,
                    state.throwable,
                    state.handlingFunction,
                    state.errorFilter,
                    state.useChildErrorLayout,
                    state.showLogoutPopUp,
                    state.skipErrorShowing
                )
                onCollect(state)
            }
            is NoOp -> {
            }
            else -> {
                onCollect(state)
            }
        }
    }

    abstract fun onCollect(state: State)
}

open class State(open val skipLoaderRemoval: Boolean = false)
class DataList : State() {
    val list = mutableListOf<State>()
    fun add(data: State) {
        list.add(data)
    }

    fun get(): MutableList<State> = list
}

data class OnError(
    val error: ErrorPapercut,
    val throwable: Throwable,
    val handlingFunction: (throwable: Throwable?) -> Hexa<Int, Any, Int, Int, Int, String> = {
        baseErrorHandler(throwable)
    },
    val errorFilter: ErrorFilter? = null,
    val useChildErrorLayout: Boolean = false,
    val showLogoutPopUp: Boolean = true,
    val skipErrorShowing: Boolean = false
) : State()

data class NoOp(override val skipLoaderRemoval: Boolean = false) : State(skipLoaderRemoval)
