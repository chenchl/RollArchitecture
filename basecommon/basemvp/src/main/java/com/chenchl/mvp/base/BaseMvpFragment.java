package com.chenchl.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenchl.mvp.interfaces.ActivityFragmentInter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.chenchl.rollarch.commonlib.widget.ToastUtil;

/**
 * created by hasee on 2019/11/29
 **/
public abstract class BaseMvpFragment extends Fragment implements ActivityFragmentInter {
    private Context mContext;
    private View rootView;
    private boolean isFirstCreate = true;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext() == null ? mContext : super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setXMLView(), container, false);
        } else {
            //防止页面切换时出现的error
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        bind = ButterKnife.bind(this, rootView);
        if (isFirstCreate) {
            isFirstCreate = false;
            initView();
            initdata();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destoryP();
    }

    public abstract void loadData();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    /**
     * 短Toast 弹窗
     *
     * @param s
     */
    public void showToast(final String s) {
        ToastUtil.showShort(s);
    }

    /**
     * 长Toast 弹窗
     *
     * @param s
     */
    public void showLongToast(final String s) {
        ToastUtil.showLong(s);
    }
}
