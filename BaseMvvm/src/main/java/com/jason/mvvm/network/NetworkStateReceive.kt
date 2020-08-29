package com.jason.mvvm.network.receive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.jason.mvvm.util.NetworkUtil

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  16:37
 *  desc   : 网络监听
 *  version: 1.0
 */
class NetworkStateReceive :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        println("网络状态发生变化")
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent?.action){
            val networkType = NetworkUtil.getNetworkType(context)
            NetworkStateManager.instance.mNetworkStateCallback.postValue(networkType)
        }
    }
}