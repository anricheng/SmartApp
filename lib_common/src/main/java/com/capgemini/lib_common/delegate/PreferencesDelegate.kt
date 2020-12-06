package com.capgemini.lib_common.delegate

import android.content.Context
import com.blankj.utilcode.util.GsonUtils
import com.capgemini.lib_common.base.GlobalApplicationContext
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * SharePreference的代理，可代理的类型有：
 * 基本类型以及polo bean,任何具有泛型的类皆不可代理
 */
class SharePreferenceDelegate<T>(private val prefName: String = "Smart") : ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        GlobalApplicationContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val classOfT = property.returnType.classifier as KClass<*>
        return when (classOfT) {
            Long::class -> prefs.getLong(property.name, -1L)
            Float::class -> prefs.getFloat(property.name, -1f)
            Int::class -> prefs.getInt(property.name, -1)
            Boolean::class -> prefs.getBoolean(property.name, false)
            String::class -> prefs.getString(property.name, "")
            else -> GsonUtils.fromJson(prefs.getString(property.name, ""), classOfT.javaObjectType)
        } as T
    }


    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(property.name, value)
                is Float -> putFloat(property.name, value)
                is Int -> putInt(property.name, value)
                is Boolean -> putBoolean(property.name, value)
                is String -> putString(property.name, value)
                else -> {
                    val classOfT = property.returnType.classifier as KClass<*>
                    putString(property.name, GsonUtils.toJson(value, classOfT.javaObjectType))
                }
            }
        }.apply()
    }
}
