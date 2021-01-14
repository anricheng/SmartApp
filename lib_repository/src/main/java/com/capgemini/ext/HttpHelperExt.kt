package com.capgemini.ext

import androidx.lifecycle.ViewModel

/**
 * execute in main thread
 * @param start doSomeThing first
 */
infix fun ViewModel.start(start: (() -> Unit)): ViewModel{
    this
    return this
}