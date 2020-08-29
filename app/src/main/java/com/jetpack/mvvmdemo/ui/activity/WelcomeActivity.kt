package com.jetpack.mvvmdemo.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import com.jetpack.mvvmdemo.R
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jetpack.mvvmdemo.base.BaseActivity
import com.jetpack.mvvmdemo.databinding.ActivityWelcomeBinding
import com.jason.mvvm.util.NetworkUtil

class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT!=0){
            finish()
            return
        }
        //mDatabind.splashLottie.postDelayed(Runnable {  },300)
        mDatabind.splashLottie.addAnimatorListener(object :AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                finish()
                //带点渐变动画
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        })
    }

    override fun showLoading(message: String) {

    }

    override fun dismissLoading() {

    }

    override fun createObserver() {}

    override fun onNetworkStateChanged(netState: NetworkUtil.NetworkType) {
        super.onNetworkStateChanged(netState)
    }


}