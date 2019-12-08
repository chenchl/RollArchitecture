package cn.chenchl.basemvp.main;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chenchl.mvp.base.BaseMvpActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import cn.chenchl.basemvp.R;
import cn.chenchl.rollarch.commonlib.cache.LocalDataProxy;

public class MainActivity extends BaseMvpActivity implements MainContract.View {
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottom)
    BottomNavigationView bottom;

    private MainPresenter mainPresenter;

    @Override
    public void setText(String text) {
        tvText.setText(text);
    }

    @Override
    public void initBefore() {
    }

    @Override
    public int setXMLView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initdata() {
        mainPresenter = new MainPresenter();
        mainPresenter.attchView(this);
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.doSomething();
            }
        });
        tvText.setText(LocalDataProxy.getInstance().get("logindata", "no login"));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem item = bottom.getMenu().getItem(position);
                item.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_bottom_1:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.item_bottom_2:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.item_bottom_3:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.item_bottom_4:
                        viewpager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            Fragment[] fragments = new Fragment[]{
                    new MainFragment(),
                    new MainFragment(),
                    new MainFragment(),
                    new MainFragment()
            };

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
    }

    @Override
    public void destoryP() {
        mainPresenter.detchView();
    }

    @Override
    public void refresh() {

    }

    @Override
    public String setTag() {
        return "主页";
    }

}
