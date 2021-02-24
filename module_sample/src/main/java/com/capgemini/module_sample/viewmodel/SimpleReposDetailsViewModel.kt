package com.capgemini.module_sample.viewmodel

data class SimpleReposDetailsViewModel(

    //用户名
    var userName: String,

    //仓库名
    var reposName: String,

    //是否私有
    var private: Boolean,

    //复制数
    var folk: Int,

    //星标数
    var star: Int,

    //议题数
    var issue: Int,

    //拉取请求
    var pullRequests: Int,

    //关注者
    var watch: Int,

)