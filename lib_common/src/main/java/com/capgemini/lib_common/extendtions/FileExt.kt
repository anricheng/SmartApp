package com.capgemini.lib_common.extendtions

import android.util.Log
import java.io.File

private const val TAG = "FileExt"

fun File.ensureDir(): Boolean {
    try {
        isDirectory.isFalse {
            isFile.isTrue {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        e.message?.let { Log.w(TAG, it) }
    }
    return false
}