package com.domba.googlecut.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.domba.googlecut.R
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.cavar.papercut.extensions.showFakeSnackBar
import com.cavar.papercut.extensions.triggerTextChanges
import com.domba.googlecut.navigation.NavigationCommand
import com.cavar.papercut.utils.isKeyboardShown
import kotlin.reflect.KClass
import com.domba.googlecut.view_model.BaseEventViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseEventFragment<T : BaseEventViewModel>(klass: KClass<T>) :
    BaseDisposableFragment<T>(klass), KoinComponent {

    val inputMethodManager by inject<InputMethodManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showSnack.observe(this, Observer {
            showFakeSnackBar(it.string, it.colorId, it.duration)
        })

        viewModel.showKeyboard.observe(this, Observer {
            view?.requestFocus()
            inputMethodManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        })

        viewModel.hideKeyboard.observe(this, Observer {
            if (requireActivity().currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
            } else {
                if (isKeyboardShown(inputMethodManager))
                    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            }
        })

        viewModel.backPress.observe(this, Observer {
            activity?.onBackPressed()
        })

        viewModel.etTrigger.observe(this, Observer {
            try {
                var rootView: View?
                if (it.fragmentTag != null)
                    rootView = childFragmentManager.findFragmentByTag(it.fragmentTag)?.view
                else
                    rootView = view

                val view = rootView?.findViewById<EditText>(it.viewId)
                view?.triggerTextChanges()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        viewModel.showBottomSheet.observe(this, Observer {
            val bottomSheet = it.bottomSheetClass
            it.bundle?.let { bottomSheet.arguments = it }
            bottomSheet.show(childFragmentManager, it.tag)
        })

        viewModel.showDateDialogFragment.observe(this, Observer {
            val dateDialog = it.dialogClass
            it.bundle?.let {
                dateDialog.arguments = it
            }
            dateDialog.show(childFragmentManager, it.tag)
        })

        viewModel.hideDialog.observe(this, Observer {
            try {
                val dialogFragment = childFragmentManager.findFragmentByTag(it) as DialogFragment
                dialogFragment.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        viewModel.showDialog.observe(this, Observer {
            val dialog = it.dialogInstance
            dialog.arguments = it.bundle
            dialog.show(childFragmentManager, it.tag)
        })

        viewModel.expliciteChooser.observe(this, Observer {
            val intent = Intent()
            intent.action = it.intentAction
            if (it.type.isNotEmpty())
                intent.type = it.type
            if (it.data != null)
                intent.data = it.data
            if (it.params.isNotEmpty())
                it.params.forEach {
                    intent.putExtra(it.first, it.second)
                }
            startActivityForResult(
                Intent.createChooser(intent, getString(it.titleId)),
                it.requestCode
            )
        })

        viewModel.loadFragment.observe(this, Observer {
            if (it.bundle != null)
                it.fragment.arguments = it.bundle
            val transaction = childFragmentManager.beginTransaction()
                .add(it.hostId, it.fragment, it.tag)
            if (it.addToBackstack)
                transaction.addToBackStack(it.tag)
            transaction.commit()
        })

        viewModel.navigation.observe(this, Observer {
            when (it) {
                is NavigationCommand.To -> {
                    val options = NavOptions.Builder()
                        .setEnterAnim(R.anim.fade_in)
                        .setExitAnim(R.anim.fade_out)
                        .setPopEnterAnim(R.anim.fade_in)
                        .setPopExitAnim(R.anim.fade_out)

                    if (it.popBackStack)
                        Navigation.findNavController(requireActivity(), it.popNavHost).popBackStack()
                    Navigation.findNavController(requireActivity(), it.actionNavHost)
                        .navigate(it.actionId, it.bundle, options.build())
                    //This is used because half of app use Android Navigation
                    if (it.finishActivity) {
                        activity?.finish()
                    }
                }
                is NavigationCommand.Back -> findNavController().navigateUp()
            }
        })

        viewModel.openExternalUrl.observe(this, Observer {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        viewModel.setText.observe(this, Observer {
            val textView = view?.findViewById<TextView>(it.first)
            textView?.text = it.second
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.performClick.observe(viewLifecycleOwner, Observer {
            view?.findViewById<View>(it)?.performClick()
        })
    }
}