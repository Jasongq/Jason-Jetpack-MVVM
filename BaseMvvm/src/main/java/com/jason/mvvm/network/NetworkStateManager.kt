
import com.jason.mvvm.KtxProvider
import com.jason.mvvm.ext.livedata.UnPeekLiveData
import com.jason.mvvm.util.NetworkUtil

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  16:41
 *  desc   : 网络变化管理者
 *  version: 1.0
 */
class NetworkStateManager private constructor(){

    val mNetworkStateCallback = UnPeekLiveData<NetworkUtil.NetworkType>()

    init {
        if (mNetworkStateCallback.value == null) {
            mNetworkStateCallback.postValue(NetworkUtil.getNetworkType(KtxProvider.getInstance()))
        }
    }

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}