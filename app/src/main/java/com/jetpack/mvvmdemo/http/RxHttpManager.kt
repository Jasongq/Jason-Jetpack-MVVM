package com.jetpack.mvvmdemo.http

import android.app.Application
import com.jetpack.mvvmdemo.BuildConfig
import com.jetpack.mvvmdemo.http.interceptor.HttpLoggingInterceptor
import com.jetpack.mvvmdemo.util.OkLogger
import okhttp3.OkHttpClient
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/07  15:06
 *  desc   : 管理Rxhttp
 *  version: 1.0
 */
object RxHttpManager {
//    @OkClient(name = "BaiduClient", className = "Baidu")
//    var simpleClient: OkHttpClient? = OkHttpClient.Builder().build()

    fun init(context: Application) {


        val file = File(context.externalCacheDir, "MyCookie")
        //val sslParams = HttpsUtils.getSslSocketFactory(arrayOf( Buffer().writeUtf8("").inputStream()),null, null)
        val client = OkHttpClient.Builder()
            .cookieJar(CookieStore(file))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            //.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
            //.hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }) //忽略host验证
            //            .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
            //            .addInterceptor(new RedirectInterceptor())
            .addInterceptor(addInterceptor())
            .build()
        //RxHttp初始化，自定义OkHttpClient对象，非必须
        RxHttp.init(client, BuildConfig.DEBUG)

        //设置缓存策略，非必须
//        val cacheFile = File(context.externalCacheDir, "MyCache")
//        RxHttpPlugins.setCache(cacheFile, 1000 * 100.toLong(), CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE)
//        RxHttpPlugins.setExcludeCacheKeys("time") //设置一些key，不参与cacheKey的组拼

        //设置数据解密/解码器，非必须
//        RxHttp.setResultDecoder(s -> s);

        //设置全局的转换器，非必须
//        RxHttp.setConverter(FastJsonConverter.create());

        //设置公共参数，非必须
        RxHttp.setOnParamAssembly { p: Param<*> ->
            /*根据不同请求添加不同参数，子线程执行，每次发送请求前都会被回调
             如果希望部分请求不回调这里，发请求前调用Param.setAssemblyEnabled(false)即可*/
            //val method = p?.getMethod()
           p.add("time", System.currentTimeMillis())
                //添加公共请求头
                .addHeader("User-Agent", "Android")
                //.addHeader("Authorization", "Bearer ")
        }
    }

    private fun addInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor("body")
        if (BuildConfig.IS_DEBUG) {
            OkLogger.debug(true)
            //log打印级别，决定了log显示的详细程度 默认：HttpLoggingInterceptor.Level.BODY
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            //log颜色级别，决定了log在控制台显示的颜色  默认：Level.INFO
            loggingInterceptor.setColorLevel(Level.INFO)
        } else {
            OkLogger.debug(false)
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE)
            loggingInterceptor.setColorLevel(Level.OFF)
        }
        return loggingInterceptor
    }
}