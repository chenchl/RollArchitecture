package com.chenchl.mvp.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.chenchl.mvp.interfaces.ActivityFragmentInter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.chenchl.rollarch.commonlib.widget.ToastUtil;

/**
 * created by hasee on 2019/11/29
 **/
public abstract class BaseMvpActivity extends AppCompatActivity implements ActivityFragmentInter {

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        initBefore();
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setXMLView());
        unbinder = ButterKnife.bind(this);
        initView();
        initdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryP();
        if (unbinder != null)
            unbinder.unbind();
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
