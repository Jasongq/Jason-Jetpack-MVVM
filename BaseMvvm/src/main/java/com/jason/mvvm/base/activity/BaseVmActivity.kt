package com.jason.mvvm.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jason.mvvm.ext.KtxAppLifeObserver
import com.jason.mvvm.ext.getVmClazz
import com.jason.mvvm.util.NetworkUtil

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  14:01
 *  desc   : ViewModelActivity基类，把ViewModel注入进来
 *  version: 1.0
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {
    /**
     * 是否需要使用Databinding 供子类BaseDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initOtherSetting()

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "加载中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        initOtherSetting()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer { onNetworkStateChanged(it) })
        KtxAppLifeObserver.isForeground.observe(this, Observer { onAppLifecycleChanged(it) })
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
//        return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetworkUtil.NetworkType) {}

    /**
     * 前后台变化监听 子类重写
     */
    open fun onAppLifecycleChanged(isForeground: Boolean) {}

    /**
     * 注册 UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            //showLoading(if (it.isEmpty()){ "加载中..." }else it)
            showLoading()
        })
        //取消弹窗
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }


    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }
    /**
     * 供子类BaseDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}

}