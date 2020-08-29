package com.jason.mvvm.ext;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

import com.jason.mvvm.util.LogExtKt;
/**
 * @author : GuoQiang
 * e-mail : 849199845@qq.com
 * time   : 2020/07/06  09:23
 * desc   : Activity 栈管理
 * version: 1.0
 */
public class ActivityStackController implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "ActivityStackController";
    private static volatile ActivityStackController sInstance;

    private final ArrayMap<String, Activity> mActivitySet = new ArrayMap<>();

    /** 当前应用上下文对象 */
    private Application mApplication;
    /** 当前 Activity 对象标记 */
    private String mCurrentTag;
    /**用于判断前后台*/
    //private int activityAmount = 0;

    private ActivityStackController() {}

    public static ActivityStackController getInstance() {
        // 加入双重校验锁
        if(sInstance == null) {
            synchronized (ActivityStackController.class) {
                if(sInstance == null){
                    sInstance = new ActivityStackController();
                }
            }
        }
        return sInstance;
    }

    public void init(Application application) {
        mApplication = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 获取 Application 对象
     */
    public Application getApplication() {
        return mApplication;
    }

    /**
     * 获取栈顶的 Activity
     */
    public Activity getTopActivity() {
        return mActivitySet.get(mCurrentTag);
    }

    /**
     * 销毁所有的 Activity
     */
    public void finishAllActivities() {
        finishAllActivities((Class<? extends Activity>) null);
    }

    /**
     * 销毁所有的 Activity，除这些 Class 之外的 Activity
     */
    @SafeVarargs
    public final void finishAllActivities(Class<? extends Activity>... classArray) {
        String[] keys = mActivitySet.keySet().toArray(new String[]{});
        for (String key : keys) {
            Activity activity = mActivitySet.get(key);
            if (activity != null && !activity.isFinishing()) {
                boolean whiteClazz = false;
                if (classArray != null) {
                    for (Class<? extends Activity> clazz : classArray) {
                        if (activity.getClass() == clazz) {
                            whiteClazz = true;
                        }
                    }
                }
                // 如果不是白名单上面的 Activity 就销毁掉
                if (!whiteClazz) {
                    activity.finish();
                    mActivitySet.remove(key);
                }
            }
        }
    }

    /**
     * 获取一个对象的独立无二的标记
     */
    private static String getObjectTag(Object object) {
        // 对象所在的包名 + 对象的内存地址
        return object.getClass().getName() + Integer.toHexString(object.hashCode());
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        LogExtKt.logd("onActivityCreated: "+activity.getLocalClassName(),TAG);
        mCurrentTag = getObjectTag(activity);
        mActivitySet.put(getObjectTag(activity), activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        LogExtKt.logd("onActivityStarted: "+activity.getLocalClassName(),TAG);
        mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        LogExtKt.logd("onActivityResumed: "+activity.getLocalClassName(),TAG);
        mCurrentTag = getObjectTag(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        LogExtKt.logd("onActivityPaused: "+activity.getLocalClassName(),TAG);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        LogExtKt.logd("onActivityStopped: "+activity.getLocalClassName(),TAG);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        LogExtKt.logd("onActivityDestroyed: "+activity.getLocalClassName(),TAG);
        mActivitySet.remove(getObjectTag(activity));
        if (getObjectTag(activity).equals(mCurrentTag)) {
            // 清除当前标记
            mCurrentTag = null;
        }
    }
}