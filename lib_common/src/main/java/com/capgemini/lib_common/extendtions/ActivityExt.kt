package com.capgemini.lib_common.extendtions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().apply {
        addToBackStack(fragment::class.java.name)
        replace(containerId, fragment, fragment::class.java.name)
        commit()
    }
}

fun FragmentActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.findFragmentByTag(fragment::class.java.name).notNull {
        supportFragmentManager.beginTransaction().apply {
            show(it!!)
            commit()
        }
    }

}

fun FragmentActivity.hideFragment(fragment: Fragment) {
    supportFragmentManager.findFragmentByTag(fragment::class.java.name).notNull {
        supportFragmentManager.beginTransaction().apply {
            hide(it!!)
            commit()
        }
    }
}