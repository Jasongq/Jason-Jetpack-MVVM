package com.jason.mvvm

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.IntentFilter
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import androidx.lifecycle.ProcessLifecycleOwner
import com.jason.mvvm.ext.ActivityStackController
import com.jason.mvvm.ext.KtxAppLifeObserver
import com.jason.mvvm.network.receive.NetworkStateReceive

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/03  11:26
 *  desc   : Using ContentProvider init Library
 *  version: 1.0
 */
class KtxProvider : ContentProvider() {

    companion object{
        private lateinit var app: Application
        private var mNetworkStateReceive: NetworkStateReceive? = null
        var watchActivityLife = true
        var watchAppLife = true

        fun getInstance(): Application {
            return this.app
        }
    }

    override fun onCreate(): Boolean {
        app= context?.applicationContext as Application
        mNetworkStateReceive= NetworkStateReceive()
        app.registerReceiver(mNetworkStateReceive, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        if(watchActivityLife) ActivityStackController.getInstance().init(app)
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(KtxAppLifeObserver)

        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? =null

    override fun insert(uri: Uri, values: ContentValues?): Uri? =null

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int =0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int =0

    override fun getType(uri: Uri): String? =null
}