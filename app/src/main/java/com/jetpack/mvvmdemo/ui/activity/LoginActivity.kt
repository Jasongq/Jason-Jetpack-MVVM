package com.jetpack.mvvmdemo.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer

import com.jetpack.mvvmdemo.R
import com.jetpack.mvvmdemo.base.BaseActivity
import com.jetpack.mvvmdemo.databinding.ActivityLoginBinding
import com.jetpack.mvvmdemo.viewmodel.LoginViewModel
import com.jetpack.mvvmdemo.util.CacheUtil
import com.jetpack.mvvmdemo.viewmodel.parseState

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/30  14:29
 *  desc   :
 *  version: 1.0
 */
class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    override fun layoutId(): Int = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.viewModel=mViewModel
        mDatabind.click=ProxyOnClick()

    }

    override fun createObserver() {
        mViewModel.loginResult.observe(this, Observer {resultState->
            parseState(resultState,{
                CacheUtil.setUser(it)
                CacheUtil.setIsLogin(true)
                mViewModel.saveUserinfo(it)
                toastSuccess("登陆成功")
            },{
                toastError(it.errorMsg)
            })
        })
    }

    inner class ProxyOnClick{
        fun goLogin(){
            when{
                mViewModel.username.get().isEmpty() -> toast("请输入账号")
                mViewModel.password.get().isEmpty() -> toast("请输入密码")
                else ->mViewModel.login(mViewModel.username.get(),mViewModel.password.get())
            }
        }
    }
}