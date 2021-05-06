package com.domba.googlecut.navigation

import android.os.Bundle
import com.domba.googlecut.R

sealed class NavigationCommand {
    data class To(val actionId: Int, val bundle: Bundle? = null, val popBackStack: Boolean = false,
                  val actionNavHost: Int = R.id.nav_host_fragment, val popNavHost: Int = R.id.nav_host_fragment,
                  val finishActivity: Boolean = false) : NavigationCommand()

    object Back : NavigationCommand()
}