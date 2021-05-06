package com.domba.dummy.dummy_fragment

import androidx.lifecycle.MutableLiveData
import com.domba.api.constants.EMPTY_STRING
import com.domba.api_common.interactors.match.MatchInteractor
import com.domba.googlecut.extensions.PublishSubject
import com.domba.googlecut.extensions.onNext
import com.domba.googlecut.view_model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DummyVM(val interactor: MatchInteractor) : GoogleViewModel() {

    @ExperimentalCoroutinesApi
    val onTestClick = PublishSubject<Unit>()

    @ExperimentalCoroutinesApi
    val initChannel = PublishSubject<Unit>()

    val viewStateLiveData = MutableLiveData<ViewState>()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun addFlow(list: ArrayList<Flow<State>>): ArrayList<Flow<State>> {

        val onTestFlow = onTestClick
            .asFlow()
            .map { SnackData("Click") }
            .flowOn(Dispatchers.IO)

        val initFlow = initChannel
            .asFlow()
            .flatMapMerge {
                flow<State> {
                    val match = interactor.getMatchById(8843655)
                    emit(
                        ViewState(
                            match.homeTeam.name ?: EMPTY_STRING,
                            match.awayTeam.name ?: EMPTY_STRING
                        )
                    )
                }.catch {
                    emit(OnError(ErrorLayout, it))
                }.flowOn(Dispatchers.IO)
            }

        list.add(onTestFlow)
        list.add(initFlow)
        return list
    }

    override fun onCollect(state: State) {
        when (state) {
            is SnackData -> _showSnack.value = state
            is ViewState -> {
                _isLoading.value = false
                hideErrorLayout()
                viewStateLiveData.value = state
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onRefresh() {
        initData()
    }

    @ExperimentalCoroutinesApi
    fun onTestClick() {
        onTestClick.onNext()
    }

    @ExperimentalCoroutinesApi
    fun initData() {
        _isLoading.value = true
        initChannel.onNext()
    }
}

data class ViewState(
    val home: String,
    val away: String
) : State()