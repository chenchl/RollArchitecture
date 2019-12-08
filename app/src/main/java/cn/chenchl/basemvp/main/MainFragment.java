package cn.chenchl.basemvp.main;

import android.widget.TextView;

import com.chenchl.mvp.base.BaseMvpFragment;

import butterknife.BindView;
import cn.chenchl.basemvp.R;

/**
 * created by hasee on 2019/12/4
 **/
public class MainFragment extends BaseMvpFragment implements MainContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private MainPresenter mainPresenter;
    private boolean isFirstLoad = true;


    @Override
    public void loadData() {
    }

    @Override
    public void initBefore() {

    }

    @Override
    public int setXMLView() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initdata() {
        mainPresenter = new MainPresenter();
        mainPresenter.attchView(this);
    }

    @Override
    public void destoryP() {
        mainPresenter.detchView();
    }

    @Override
    public void refresh() {
        if (isFirstLoad) {
            isFirstLoad = false;
            mainPresenter.doSomething();
        }
    }

    @Override
    public String setTag() {
        return "主页fragment";
    }

    @Override
    public void setText(String text) {
        tvTitle.setText(text);
    }
}
