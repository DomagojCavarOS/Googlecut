package com.cavar.papercut.extensions

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.domba.googlecut.R
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelOwnerDefinition
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

inline var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

inline var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

internal fun Fragment.showFakeSnackBar(
    string: Any,
    colorId: Int = R.color.black_cobalt,
    time: Int = Toast.LENGTH_SHORT
) {
    val layout = LayoutInflater.from(context).inflate(R.layout.snack_layout, null)
    val title = layout.findViewById<TextView>(R.id.tvSnackText)
    val root = layout.findViewById<LinearLayout>(R.id.llSnackContainer)
    root.setBackgroundColor(ContextCompat.getColor(context!!, colorId))
    title.text = getString(string, context!!)
    with(Toast(context)) {
        setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, 0)
        duration = time
        view = layout
        show()
    }
}

private fun getString(string: Any, context: Context): String {
    return when (string) {
        is String -> string
        is Int -> context.resources.getString(string)
        else -> ""
    }
}

fun Fragment.getString(string: Any?): String? {
    return when (string) {
        is String -> string
        is Int -> context!!.resources.getString(string)
        else -> null
    }
}

fun EditText.triggerTextChanges() {
    setText(text.toString())
}