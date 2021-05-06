package com.domba.dummy.dummy_fragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.domba.dummy.R
import com.domba.dummy.dummy_fragment.DummyVM
import com.domba.googlecut.fragment.GooglecutFragment
import kotlinx.android.synthetic.main.fragment_dummy.*

class DummyFragment : GooglecutFragment<DummyVM>(DummyVM::class) {

    override val layoutRes: Int = R.layout.fragment_dummy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTest.setOnClickListener { viewModel.onTestClick() }

        viewModel.viewStateLiveData.observe(viewLifecycleOwner, Observer {
            tvHome.text = it.home
            tvAway.text = it.away
        })
    }
}