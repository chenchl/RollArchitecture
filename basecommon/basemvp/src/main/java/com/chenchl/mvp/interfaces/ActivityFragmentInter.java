package com.chenchl.mvp.interfaces;

import androidx.annotation.UiThread;

/**
 * create by ccl 2016.3.23
 */
public interface ActivityFragmentInter {

    void initBefore();

    int setXMLView();

    @UiThread
    void initView();

    void initdata();

    void destoryP();

    void refresh();

    String setTag();
}
