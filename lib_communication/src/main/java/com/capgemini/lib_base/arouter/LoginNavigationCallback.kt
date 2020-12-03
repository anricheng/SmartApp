package com.capgemini.lib_base.arouter

import androidx.startup.Initializer
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.capgemini.lib_base.arouter.ProfileModuleARouterPath.Companion.PROFILE_LOGIN
import com.capgemini.lib_base.arouter.RouterExtra.Companion.INTERCEPTED_PATH
import com.capgemini.lib_common.extendtions.notNull
import com.capgemini.lib_common.extendtions.otherwise

class LoginNavigationCallback : SimpleNavigationCallback(){
    override fun onInterrupt(postcard: Postcard) {
        postcard.extras.notNull { ARouter.getInstance().build(PROFILE_LOGIN).with(it).withString(INTERCEPTED_PATH, postcard.path).greenChannel().navigation() }
                       .otherwise {ARouter.getInstance().build(PROFILE_LOGIN).withString(INTERCEPTED_PATH, postcard.path).greenChannel().navigation()}

    }
}