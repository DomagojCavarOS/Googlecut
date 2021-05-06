package com.domba.googlecut.fragment

import android.os.Bundle
import com.domba.googlecut.view_model.BaseDisposableViewModel
import kotlin.reflect.KClass

abstract class BaseDisposableFragment<T : BaseDisposableViewModel>(klass: KClass<T>) : BaseKoinFragment<T>(klass){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
}