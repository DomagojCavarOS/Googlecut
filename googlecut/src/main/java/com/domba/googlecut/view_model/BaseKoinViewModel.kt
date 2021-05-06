package com.domba.googlecut.view_model

import android.content.res.Resources
import androidx.lifecycle.ViewModel

abstract class BaseKoinViewModel : ViewModel() {
    lateinit var resources: Resources
}