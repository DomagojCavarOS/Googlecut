package com.domba.googlecut.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.cavar.papercut.extensions.getString
import com.cavar.papercut.extensions.isVisible
import com.cavar.papercut.extensions.showFakeSnackBar
import com.domba.googlecut.R
import com.domba.googlecut.view_model.BaseErrorViewModel
import kotlin.reflect.KClass

abstract class BaseErrorFragment<T : BaseErrorViewModel>(klass: KClass<T>) :
    BaseLoaderFragment<T>(klass) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showError.observe(this, Observer {
            showFakeSnackBar(it.string, it.colorId, it.duration)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showErrorLayout.observe(viewLifecycleOwner, Observer { errorData ->
            requireActivity().window?.setBackgroundDrawable(null)
            view.findViewById<TextView>(R.id.tvRefresh)?.isVisible =
                errorData?.buttonText != R.string.empty
            if (errorData?.hasError == true) {
                view.findViewById<RelativeLayout>(R.id.rlErrorRoot)?.isVisible = true
                view.findViewById<ImageView>(R.id.ivErrorIcon)
                    ?.setImageDrawable(ContextCompat.getDrawable(context!!, errorData.imageSrc))
                view.findViewById<TextView>(R.id.tvErrorTitle)?.text = getString(errorData.title)
                view.findViewById<TextView>(R.id.tvErrorDescription)?.text =
                    getString(errorData.description)
                view.findViewById<TextView>(R.id.tvRefresh)?.text = getString(errorData.buttonText)
                view.findViewById<TextView>(R.id.tvRefresh)
                    ?.setOnClickListener { errorData.listener.onRefresh() }
            } else
                view.findViewById<RelativeLayout>(R.id.rlErrorRoot)?.isVisible = false
        })

        viewModel.showErrorLayoutChild.observe(viewLifecycleOwner, Observer { errorData ->
            requireActivity().window?.setBackgroundDrawable(null)
            if (errorData?.hasError == true) {
                view.findViewById<RelativeLayout>(R.id.rlErrorRootChild)?.isVisible = true
                view.findViewById<ImageView>(R.id.ivErrorIconChild)
                    ?.setImageDrawable(ContextCompat.getDrawable(context!!, errorData.imageSrc))
                view.findViewById<TextView>(R.id.tvErrorTitleChild)?.text =
                    getString(errorData.title)
                view.findViewById<TextView>(R.id.tvErrorDescriptionChild)?.text =
                    getString(errorData.description)
                view.findViewById<TextView>(R.id.tvRefreshChild)?.text =
                    getString(errorData.buttonText)
                view.findViewById<TextView>(R.id.tvRefreshChild)
                    ?.setOnClickListener { errorData.listener.onRefresh() }
            } else
                view.findViewById<RelativeLayout>(R.id.rlErrorRootChild)?.isVisible = false
        })
    }
}