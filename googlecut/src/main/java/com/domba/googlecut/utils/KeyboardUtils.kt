package com.cavar.papercut.utils

import android.graphics.Rect
import android.view.View
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import android.view.inputmethod.InputMethodManager

fun isKeyboardShown(inputMethodManager: InputMethodManager): Boolean {
    val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
    val height = windowHeightMethod.invoke(inputMethodManager) as Int
    return height > 0
}