package com.elephant.healthycat.healthycattest.mvp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.andview.refreshview.utils.LogUtils;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.elephant.healthycat.healthycattest.BaseActivity;
import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.fragment.DiscoveryFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.HomeFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.MessageFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.MyFragment;
import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.presenter.HomePresenter;
import com.elephant.healthycat.healthycattest.util.CycleViewPager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private String TAG = MainActivity.class.getSimpleName();
    private int FirstSelectedPosition = 0;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private DiscoveryFragment discoveryFragment;
    private MyFragment myFragment;
    public static CycleViewPager cycleViewPager;
    private HomePresenter mHomePresenter;
    private List<CarouselModl> mCarouselModelList;
    private List<ImageView> views = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configImageLoader();
        initView();
        //如果不想XRefreshView后台输出log，此处传入false即可
        LogUtils.enableLog(true);


    }

    private void initView() {
        BottomNavigationBar navigationBar = (BottomNavigationBar) findViewById(R.id.navigationbar);
        navigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);
        navigationBar.setActiveColor(R.color.green);
        navigationBar.setInActiveColor(R.color.black);
        navigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.message)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.discovery)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.my)))
                .setFirstSelectedPosition(FirstSelectedPosition)//默认选中
                .initialise();
        navigationBar.setTabSelectedListener(this);
        setDefaultFragment();


    }

    //设置默认fragment
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance(getString(R.string.message));
            ft.add(R.id.tab, homeFragment);
        }
        hideFragment(ft);
        ft.show(homeFragment);
        ft.commit();
    }

    private void setMessageFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (messageFragment == null) {
            messageFragment = MessageFragment.newInstance(getString(R.string.home));
            ft.add(R.id.tab, messageFragment);
        }
        hideFragment(ft);
        ft.show(messageFragment);
        ft.commit();
    }

    private void setDiscoveryFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (discoveryFragment == null) {
            discoveryFragment = DiscoveryFragment.newInstance(getString(R.string.my));
            ft.add(R.id.tab, discoveryFragment);
        }
        hideFragment(ft);
        ft.show(discoveryFragment);
        ft.commit();
    }

    private void setMyFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (myFragment == null) {
            myFragment = MyFragment.newInstance(getString(R.string.discovery));
            ft.add(R.id.tab, myFragment);
        }
        hideFragment(ft);
        ft.show(myFragment);
        ft.commit();
    }

    //隐藏fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (discoveryFragment != null) {
            transaction.hide(discoveryFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected position:" + position);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case 0:
                setDefaultFragment();//首页
                break;
            case 1:
                setMessageFragment();//消息
                break;
            case 2:
                setDiscoveryFragment();//发现
                break;
            case 3:
                setMyFragment();//我的
                break;
            default:
                break;
        }
        ft.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected position:" + position);
    }

    @Override
    public void onTabReselected(int position) {
    }

    @Override
    protected void handleMessage(Message message) {

    }

    // 初始化ImageLoader
    private void configImageLoader() {
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
