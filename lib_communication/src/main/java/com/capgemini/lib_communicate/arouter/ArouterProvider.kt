package com.capgemini.lib_communicate.arouter

import com.alibaba.android.arouter.facade.template.IProvider

interface ProfileModuleProvider : IProvider {
    /**
     * 获取UserId
     *
     * @return userId
     */
    fun getUserId(): String?

    /**
     * 获取UserToken
     *
     * @return token
     */
    fun getUserToken(): String?
}


interface SampleProvider : IProvider {
    /**
     * 获取UserId
     *
     * @return userId
     */
    fun getUserId(): String?

    /**
     * 获取UserToken
     *
     * @return token
     */
    fun getUserToken(): String?
}