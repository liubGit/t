package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.MainActivity;
import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.presenter.Presenter;
import com.elephant.healthycat.healthycattest.mvp.view.HomeView;
import com.elephant.healthycat.healthycattest.util.CycleViewPager;
import com.elephant.healthycat.healthycattest.util.ViewFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/23.
 */

public class HomeFragment extends Fragment implements HomeView {

    private MainActivity mainActivity;

    private Presenter mPresenter;
    private List<CarouselModl> mCarouselModelList;
    private List<ImageView> views = new ArrayList<ImageView>();
    private CycleViewPager cycleViewPager;

    public static HomeFragment newInstance(String param) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arge", param);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_homefragment_ll, container, false);

        ButterKnife.bind(this, view);
//        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("arge");
//        TextView tv = (TextView)view.findViewById(R.id.hometv);
//        tv.setText(agrs1);

//        setActionBar(view, getString(R.string.app_name));
//        mLeftTitleTv.setVisibility(View.VISIBLE);
//        mLeftTitleTv.setText(getString(R.string.ads));
//        mBackBtn.setImageResource(R.mipmap.icon_positioning);

        cycleViewPager= (CycleViewPager) (getActivity().getFragmentManager()).findFragmentById(R.id.fragment_cycle_viewpager_content);
//        cycleViewPager=mainActivity.cycleViewPager;
        initConfig();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    private void initConfig() {
        mPresenter = new Presenter(this);
        setData();
        getData();
    }
    @Override
    public void setData() {
        mPresenter.setData();
    }

    @Override
    public void getData() {
        mCarouselModelList = mPresenter.getData();

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), mCarouselModelList.get(mCarouselModelList.size() - 1).getUrl()));
        for (int i = 0; i < mCarouselModelList.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), mCarouselModelList.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), mCarouselModelList.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, mCarouselModelList, new CycleViewPager.ImageCycleViewListener() {
            @Override
            public void onImageClick(CarouselModl info, int postion, View imageView) {

                if (cycleViewPager.isCycle()) {
                    postion = postion - 1;
                }
            }
        });

        //设置轮播
        cycleViewPager.setWheel(true);
        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(5000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
}
