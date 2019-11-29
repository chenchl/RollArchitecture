package com.chenchl.mvp.base;

import android.os.Bundle;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * created by hasee on 2019/11/29
 * 懒加载fragment
 **/
public abstract class BaseLazyMvpFragment extends BaseMvpFragment {

    private boolean isViewCreated; // 界面是否已创建完成
    private boolean isVisibleToUser; // 是否对用户可见
    private boolean isDataLoaded; // 数据是否已请求, isNeedReload()返回false的时起作用


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryToLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
    }

    /**
     * ViewPager场景下，判断父fragment是否可见
     *
     * @return
     */
    private boolean isParentVisible() {
        Fragment fragment = getParentFragment();
        return fragment == null || (fragment instanceof BaseLazyMvpFragment && ((BaseLazyMvpFragment) fragment).isVisibleToUser);
    }

    /**
     * 加载数据
     */
    private void tryToLoad() {
        if (isViewCreated && isVisibleToUser && isParentVisible() && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
            // 通知 子 Fragment 请求数据
            dispatchParentVisibleState();
        }
    }

    /**
     * ViewPager场景下，当前fragment可见，如果其子fragment也可见，则尝试让子fragment请求数据
     */
    private void dispatchParentVisibleState() {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment child : fragments) {
            if (child instanceof BaseLazyMvpFragment && ((BaseLazyMvpFragment) child).isVisibleToUser) {
                ((BaseLazyMvpFragment) child).tryToLoad();
            }
        }
    }
}
