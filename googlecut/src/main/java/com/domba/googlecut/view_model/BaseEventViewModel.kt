package com.domba.googlecut.view_model

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.cavar.papercut.constants.EMPTY_STRING
import com.cavar.papercut.constants.INIT_FLAG
import com.cavar.papercut.constants.INIT_INT
import com.cavar.papercut.live_data.SingleLiveEvent
import com.domba.googlecut.navigation.NavigationCommand
import com.domba.googlecut.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseEventViewModel : BaseDisposableViewModel() {

    protected val _showSnack = SingleLiveEvent<SnackData>()
    val showSnack: LiveData<SnackData>
        get() = _showSnack

    protected val _hideKeyboard = SingleLiveEvent<Any>()
    val hideKeyboard: LiveData<Any>
        get() = _hideKeyboard

    protected val _showKeyboard = SingleLiveEvent<Any>()
    val showKeyboard: LiveData<Any>
        get() = _showKeyboard

    val _backPress = SingleLiveEvent<Any>()
    val backPress: LiveData<Any>
        get() = _backPress

    protected val _performClick = SingleLiveEvent<Int>()
    val performClick: LiveData<Int>
        get() = _performClick

    protected val _etSetText = SingleLiveEvent<EtTriggerSetData>()
    val etSetText: LiveData<EtTriggerSetData>
        get() = _etSetText

    protected val _requestFocus = SingleLiveEvent<Int>()
    val requestFocus: LiveData<Int>
        get() = _requestFocus

    protected val _navigation = SingleLiveEvent<NavigationCommand>()
    val navigation: LiveData<NavigationCommand>
        get() = _navigation

    protected val _etTrigger = SingleLiveEvent<EtTriggerData>()
    val etTrigger: LiveData<EtTriggerData>
        get() = _etTrigger

    protected val _showBottomSheet = SingleLiveEvent<BottomSheetData>()
    val showBottomSheet: LiveData<BottomSheetData>
        get() = _showBottomSheet

    protected val _showDateDialog = SingleLiveEvent<DateDialogData>()
    val showDateDialogFragment: LiveData<DateDialogData>
        get() = _showDateDialog

    protected val _showDialog = SingleLiveEvent<DialogData>()
    val showDialog: LiveData<DialogData>
        get() = _showDialog

    protected val _dismissDialog = SingleLiveEvent<String>()
    val hideDialog: LiveData<String>
        get() = _dismissDialog

    protected val _openExternalUrl = SingleLiveEvent<String>()
    val openExternalUrl: LiveData<String>
        get() = _openExternalUrl

    protected val _clearFocus = SingleLiveEvent<Int>()
    val clearFocus: LiveData<Int>
        get() = _clearFocus

    protected val _setText = SingleLiveEvent<Pair<Int, String>>()
    val setText: LiveData<Pair<Int, String>>
        get() = _setText


    protected val _expliciteChooser = SingleLiveEvent<IntentData>()
    val expliciteChooser: LiveData<IntentData>
        get() = _expliciteChooser

    protected val _loadFragment = SingleLiveEvent<FragmentData>()
    val loadFragment: LiveData<FragmentData>
        get() = _loadFragment

    fun hideKeyboard() {
        _hideKeyboard.call()
    }
}

//User for single events like showing snack bars, dialog pickers, etc.
data class SnackData(var string: Any = EMPTY_STRING, val colorId: Int = R.color.black_cobalt, val duration: Int = Toast.LENGTH_SHORT): State()

data class BottomSheetData(
    val bottomSheetClass: BottomSheetDialogFragment,
    val tag: String,
    val bundle: Bundle? = null
): State()

data class DialogData(
    val dialogInstance: DialogFragment,
    val tag: String,
    val bundle: Bundle? = null
)

data class FragmentData(
    val fragment: Fragment,
    val tag: String,
    val hostId: Int,
    val addToBackstack: Boolean = false,
    val bundle: Bundle? = null
)

data class DateDialogData(
    val dialogClass: DialogFragment,
    val tag: String,
    val bundle: Bundle? = null
)

data class IntentData(
    val intentAction: String,
    val type: String = EMPTY_STRING,
    val requestCode: Int = INIT_INT,
    val titleId: Int,
    val flag: Int = INIT_FLAG,
    val data: Uri? = null,
    val params: MutableList<Pair<String, String>> = mutableListOf()
) : State()

data class EtTriggerData(val viewId: Int, val fragmentTag: String? = null): State()
data class EtTriggerSetData(val viewId: Int, val text: String): State()