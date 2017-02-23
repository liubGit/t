package com.elephant.healthycat.healthycattest.mvp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.elephant.healthycat.healthycattest.BaseActivity;
import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.fragment.DiscoveryFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.HomeFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.MessageFragment;
import com.elephant.healthycat.healthycattest.mvp.fragment.MyFragment;
import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.presenter.Presenter;
import com.elephant.healthycat.healthycattest.mvp.view.HomeView;
import com.elephant.healthycat.healthycattest.util.CycleViewPager;
import com.elephant.healthycat.healthycattest.util.ViewFactory;
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
    public static  CycleViewPager cycleViewPager;
    private Presenter mPresenter;
    private List<CarouselModl> mCarouselModelList;
    private List<ImageView> views = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configImageLoader();
        initView();
//        initConfig();
    }

    private void initView() {
        BottomNavigationBar navigationBar = (BottomNavigationBar) findViewById(R.id.navigationbar);
//        cycleViewPager = (CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);

        navigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);
        navigationBar.setActiveColor(R.color.green);
        navigationBar.setInActiveColor(R.color.black);
        navigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.message)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.discovery)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.message)))
                .setFirstSelectedPosition(FirstSelectedPosition)//默认选中
                .initialise();
        navigationBar.setTabSelectedListener(this);
        setDefaultFragment();

    }

//    private void initConfig() {
//        mPresenter = new Presenter(this);
//        setData();
//        getData();
//    }

    //设置默认fragment
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        homeFragment = HomeFragment.newInstance(getString(R.string.home));
        ft.replace(R.id.tab, homeFragment);
        ft.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected position:" + position);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance(getString(R.string.home));
                }
                ft.replace(R.id.tab, homeFragment);
                break;
            case 1:
                if (messageFragment == null) {
                    messageFragment = MessageFragment.newInstance(getString(R.string.message));
                }
                ft.replace(R.id.tab, messageFragment);
                break;
            case 2:
                if (discoveryFragment == null) {
                    discoveryFragment = DiscoveryFragment.newInstance(getString(R.string.discovery));
                }
                ft.replace(R.id.tab, discoveryFragment);
                break;
            case 3:
                if (myFragment == null) {
                    myFragment = MyFragment.newInstance(getString(R.string.my));
                }
                ft.replace(R.id.tab, myFragment);
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

//    @Override
//    public void setData() {
//        mPresenter.setData();
//    }
//
//    @Override
//    public void getData() {
//        mCarouselModelList = mPresenter.getData();
//
//        // 将最后一个ImageView添加进来
//        views.add(ViewFactory.getImageView(this, mCarouselModelList.get(mCarouselModelList.size() - 1).getUrl()));
//        for (int i = 0; i < mCarouselModelList.size(); i++) {
//            views.add(ViewFactory.getImageView(this, mCarouselModelList.get(i).getUrl()));
//        }
//        // 将第一个ImageView添加进来
//        views.add(ViewFactory.getImageView(this, mCarouselModelList.get(0).getUrl()));
//
//        // 设置循环，在调用setData方法前调用
//        cycleViewPager.setCycle(true);
//
//        // 在加载数据前设置是否循环
//        cycleViewPager.setData(views, mCarouselModelList, new CycleViewPager.ImageCycleViewListener() {
//            @Override
//            public void onImageClick(CarouselModl info, int postion, View imageView) {
//
//                if (cycleViewPager.isCycle()) {
//                    postion = postion - 1;
//                }
//            }
//        });
//
//        //设置轮播
//        cycleViewPager.setWheel(true);
//        // 设置轮播时间，默认5000ms
//        cycleViewPager.setTime(5000);
//        //设置圆点指示图标组居中显示，默认靠右
//        cycleViewPager.setIndicatorCenter();
//    }

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

}
