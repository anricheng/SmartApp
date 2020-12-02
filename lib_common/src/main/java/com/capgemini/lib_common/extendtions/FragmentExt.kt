package com.capgemini.lib_common.extendtions

import androidx.fragment.app.Fragment

fun Fragment.replaceFragment(containerId: Int, fragment: Fragment) {
    this.activity?.apply {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(fragment::class.java.name)
            replace(containerId, fragment, fragment::class.java.name)
            commit()
        }
    }
}

fun Fragment.showFragment(fragment: Fragment) {
    this.activity?.apply {
        supportFragmentManager.findFragmentByTag(fragment::class.java.name).notNull {
            supportFragmentManager.beginTransaction().apply {
                show(it!!)
                commit()
            }
        }
    }

}

fun Fragment.hideFragment(fragment: Fragment) {
    this.activity?.apply {
        supportFragmentManager.findFragmentByTag(fragment::class.java.name).notNull {
            supportFragmentManager.beginTransaction().apply {
                hide(it!!)
                commit()
            }
        }
    }
}