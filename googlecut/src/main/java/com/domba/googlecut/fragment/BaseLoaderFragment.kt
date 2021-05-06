package com.domba.googlecut.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cavar.papercut.constants.EMPTY_STRING
import com.cavar.papercut.extensions.getString
import com.cavar.papercut.extensions.isVisible
import com.domba.googlecut.R
import com.domba.googlecut.view_model.BaseLoaderViewModel
import kotlinx.android.synthetic.main.loader_layout.*
import kotlin.reflect.KClass
//TODO rename this in version 2.0 in BaseStateFragment

abstract class BaseLoaderFragment<T : BaseLoaderViewModel>(klass: KClass<T>) : BaseEventFragment<T>(klass) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLoading.observe(this, Observer {
            clLoader?.isVisible = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isEmptyState.observe(viewLifecycleOwner, Observer {
            view.findViewById<RelativeLayout>(R.id.rlEmptyLayout)?.isVisible = it?.isEmpty == true
            view.findViewById<TextView>(R.id.tvEmptyTextDescription)?.text = getString(it?.description ?: EMPTY_STRING)
            view.findViewById<TextView>(R.id.tvEmptyErrorMainTitle)?.text = getString(it?.title ?: EMPTY_STRING)
        })
    }
}