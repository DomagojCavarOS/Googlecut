package com.domba.googlecut.extensions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import kotlin.experimental.ExperimentalTypeInference


@ExperimentalCoroutinesApi
public fun <E> PublishSubject(): BroadcastChannel<E> = BroadcastChannel(Channel.CONFLATED)

@ExperimentalCoroutinesApi
fun <E>BroadcastChannel<E>.onNext(e: E){
    sendBlocking(e)
}

@ExperimentalCoroutinesApi
fun BroadcastChannel<Unit>.onNext(){
    sendBlocking(Unit)
}