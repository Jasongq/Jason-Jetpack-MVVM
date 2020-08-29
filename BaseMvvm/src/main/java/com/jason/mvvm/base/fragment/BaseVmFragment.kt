package com.jason.mvvm.base.fragment

import NetworkStateManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jason.mvvm.base.dialog.BaseDialog
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jason.mvvm.ext.getVmClazz
import com.jason.mvvm.util.NetworkUtil
import com.jason.mvvm.util.logd

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  14:03
 *  desc   : ViewModelFragment基类，自动把ViewModel注入Fragment
 *  version: 1.0
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {
    /**是否第一次加载*/
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: FragmentActivity

    private var mBaseDialog : BaseDialog?=null

    abstract fun showLoading(message: String = "加载中...")

    abstract fun dismissLoading()

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity=context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst=true
        mViewModel=createViewModel()
        initView(savedInstanceState)
        createObserver()
        onVisible()
        registorDefUIChange()
        initData()
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetworkUtil.NetworkType) {}

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
        //return ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application)).get(getVmClazz(this))
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    override fun onResume() {
        super.onResume()
        onVisible()
    }
    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        //"周期:${lifecycle.currentState.name}   是否第一次:${isFirst}".logd()
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //延迟加载0.12秒加载 避免fragment跳转动画和渲染ui同时进行，出现些微的小卡顿
            view?.postDelayed({
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observe(viewLifecycleOwner, Observer {
                    onNetworkStateChanged(it)
                })
                isFirst = false
            },120)
        }
    }

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading()
        })
        //取消弹窗
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

    /**
     * 若是与ViewPager一起使用，调用的是setUserVisibleHint
     * @param isVisibleToUser
     */
    /*override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }*/
    /**
     * 注1:是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 注2:是初始就show的Fragment 为了触发该事件 需要先hide再show
     * @param hidden
     */
   /* override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }*/
}