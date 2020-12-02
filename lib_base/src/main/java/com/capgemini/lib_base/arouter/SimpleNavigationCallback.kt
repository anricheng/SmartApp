package com.capgemini.lib_base.arouter

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback

open class SimpleNavigationCallback : NavigationCallback {

    override fun onFound(postcard: Postcard?) {}

    override fun onLost(postcard: Postcard?) {}

    override fun onArrival(postcard: Postcard?) {}

    override fun onInterrupt(postcard: Postcard) {}
}