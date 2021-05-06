package com.domba.googlecut.fragment

import android.os.Bundle
import com.domba.googlecut.view_model.GoogleViewModel
import kotlin.reflect.KClass

abstract class GooglecutFragment<T : GoogleViewModel>(klass: KClass<T>) : BaseErrorFragment<T>(klass) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!viewModel.isLive)
            viewModel.initDisposable()
    }
}