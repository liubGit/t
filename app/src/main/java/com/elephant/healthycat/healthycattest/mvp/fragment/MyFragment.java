package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.bumptech.glide.Glide;
import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.model.CarouselModl;
import com.elephant.healthycat.healthycattest.mvp.my.model.MyModel;
import com.elephant.healthycat.healthycattest.mvp.my.presenter.MyPersenter;
import com.elephant.healthycat.healthycattest.mvp.my.recylearview.CustomGifHeader;
import com.elephant.healthycat.healthycattest.mvp.my.recylearview.SimpleAdapter;
import com.elephant.healthycat.healthycattest.mvp.my.view.MyView;
import com.elephant.healthycat.healthycattest.util.BaseFragment;
import com.elephant.healthycat.healthycattest.util.CustomerFooter;
import com.elephant.healthycat.healthycattest.util.MyBannerViewPager;
import com.elephant.healthycat.healthycattest.util.StatusBarUtil;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/23.
 */

public class MyFragment extends BaseFragment implements MyView {
    private View view;
    private List<MyModel> myModelList = new ArrayList<>();
    private List<CarouselModl> myCarouselModelList = new ArrayList<>();
    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private MyBannerViewPager bannerViewPager;
    private SimpleAdapter simpleAdapter;
    private MyPersenter myPersenter;
    private LinearLayoutManager llManager;
    private LinearLayout privateLayout;

    private int count = 0;
    private String TAG=MyFragment.class.getSimpleName();

    public static MyFragment newInstance(String param) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arge", param);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_myfragment_ll, container, false);
        ButterKnife.bind(this, view);
//        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("arge");
//        TextView tv = (TextView)view.findViewById(R.id.mytv);
//        tv.setText(agrs1);
        setActionBar(view, getString(R.string.app_name));
        mLeftTitleTv.setVisibility(View.VISIBLE);
        mLeftTitleTv.setText(getString(R.string.ads));
        mBackBtn.setVisibility(View.VISIBLE);
        mBackBtn.setImageResource(R.mipmap.icon_positioning);

        xRefreshView = (XRefreshView) view.findViewById(R.id.xrefreshview);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        xRefreshView.setPullLoadEnable(true);
        recyclerView.setHasFixedSize(true);
        initConfig();//配置

        View vstatusBarView = inflater.inflate(R.layout.activity_my_bannerview, container, false);
        StatusBarUtil.setTransparentForImageViewInFragment(getActivity(), vstatusBarView);
        return view;
    }

    private void initConfig() {
        myPersenter = new MyPersenter(this);
        setData();
        getData();
//        setCarouselData();
//        getCarouselData();
        simpleAdapter = new SimpleAdapter(myModelList, getActivity());
        llManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llManager);
        simpleAdapter.setHeaderView(R.layout.activity_my_testfragment_item, recyclerView);
        View view = simpleAdapter.setHeaderView(R.layout.activity_my_bannerview, recyclerView);
//        View view=simpleAdapter.setHeaderView(R.layout.activity_my_bannerview,recyclerView);
//        bannerViewPager= (MyBannerViewPager) view.findViewById(R.id.index_viewpager);
        RecyclerViewBanner recyclerViewBanner = (RecyclerViewBanner) view.findViewById(R.id.index_viewpager);//添加banner
        privateLayout = (LinearLayout) view.findViewById(R.id.mybannerview_private_ll);//约私教



        recyclerViewBanner.isShowIndicatorPoint(true);
        recyclerViewBanner.setRvBannerDatas(myModelList);
        recyclerViewBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, ImageView bannerView) {
                Glide.with(bannerView.getContext()).load(myModelList.get(position % myModelList.size()).getUrl()).placeholder(R.mipmap.ic_launcher).into(bannerView);
//                ImageLoader.getInstance().displayImage(myCarouselModelList.get(position & myCarouselModelList.size()).getUrl(), bannerView);
            }
        });
        final CustomGifHeader header = new CustomGifHeader(getActivity());
        xRefreshView.setCustomHeaderView(header);
        recyclerView.setAdapter(simpleAdapter);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        simpleAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getActivity()));

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {

                Random random = new Random();
                boolean success = random.nextBoolean();
                if (success) {
                    xRefreshView.stopRefresh();
                } else {
                    xRefreshView.stopRefresh(false);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 6; i++) {
                            MyModel model = new MyModel();
                            model.setName("moreName" + i);
                            model.setNumber("moreNumber" + i);
                            simpleAdapter.insert(model, simpleAdapter.getAdapterItemCount());
                        }
                        count++;
                        if (count >= 3) {
                            xRefreshView.setLoadComplete(true);
                        } else {
                            // 刷新完成必须调用此方法停止加载。默认隐藏footerview
                            xRefreshView.stopLoadMore();
                        }
                    }
                }, 1000);

            }
        });
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d(TAG, "onScrolled: y--->"+dy);
                if (dy<=0){
                    mrelativeLayout.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));
                }else if(dy>0){
                    mrelativeLayout.setBackgroundColor(getResources().getColor(R.color.green));
                }else{
                    mrelativeLayout.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        privateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "约私教", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setData() {
        myPersenter.setData();
    }

    @Override
    public void getData() {
        myModelList = myPersenter.getData();
    }

//    @Override
//    public void setCarouselData() {
//        myPersenter.setCarouselData();
//    }
//
//    @Override
//    public void getCarouselData() {
//        myCarouselModelList = myPersenter.getCarouselData();
//    }
}
