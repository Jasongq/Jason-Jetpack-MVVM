package com.jetpack.mvvmdemo.http

import com.jetpack.mvvmdemo.BuildConfig
import rxhttp.wrapper.annotation.DefaultDomain

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/06  16:47
 *  desc   :
 *  version: 1.0
 */
object Urls {
    @DefaultDomain
    const val baseUrl= BuildConfig.BASE_URL

    /*多域名设置,非默认域名，并取别名为BaseUrlGoogle*/
    //@Domain(name = "BaseUrlGoogle")
    //const val googleUrl="https://www.google.com/"

    const val reqLogin="user/login"


}