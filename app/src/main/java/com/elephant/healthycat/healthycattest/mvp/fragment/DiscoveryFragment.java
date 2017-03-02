package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.app.Fragment;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.adapter.NewsAdapter;
import com.elephant.healthycat.healthycattest.bean.DiscroveryBean;
import com.elephant.healthycat.healthycattest.commons.Contants;
import com.elephant.healthycat.healthycattest.mvp.discrovery.model.DiscroveryModel;
import com.elephant.healthycat.healthycattest.mvp.discrovery.model.onLoadNewsListener;
import com.elephant.healthycat.healthycattest.mvp.discrovery.presenter.DiscroveryNewsPresenter;
import com.elephant.healthycat.healthycattest.mvp.discrovery.presenter.DiscroveryNewsPresenterImpl;
import com.elephant.healthycat.healthycattest.mvp.discrovery.view.DiscroveryView;
import com.elephant.healthycat.healthycattest.util.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/23.
 */

public class DiscoveryFragment extends BaseFragment implements DiscroveryView, SwipeRefreshLayout.OnRefreshListener {
    private View view;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private DiscroveryNewsPresenter discroveryNewsPresenter;
    private int pageIndex = 0;
    private List<DiscroveryBean> discroveryBeen;

    public static DiscoveryFragment newInstance(String param) {
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arge", param);
        discoveryFragment.setArguments(bundle);
        return discoveryFragment;
    }

    public DiscoveryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_discoveryfragment_ll, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dicoveryrecycleview);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh_widght);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark,
                R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(newsAdapter);
        discroveryNewsPresenter = new DiscroveryNewsPresenterImpl(this);
        mRecyclerView.addOnScrollListener(onScrollListener);
        onRefresh();
        return view;
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int item;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && item + 1 == newsAdapter.getItemCount()
                    && newsAdapter.isShowFooter()) {
                //加载更多
                discroveryNewsPresenter.LoadNews(0, pageIndex + Contants.PAZE_SIZE);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            item = linearLayoutManager.findLastVisibleItemPosition();
        }
    };

    private NewsAdapter.OnItemClickListener onItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            DiscroveryBean discroveryBean = newsAdapter.getItem(position);

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void addNews(List<DiscroveryBean> list) {
        newsAdapter.isShowFooter(true);
        if (discroveryBeen == null) {
            discroveryBeen = new ArrayList<>();
        }
        discroveryBeen.addAll(list);
        if (pageIndex == 0) {
            newsAdapter.setmDate(discroveryBeen);
        } else {
            if (list == null || list.size() == 0) {
                newsAdapter.isShowFooter(false);
            }
            newsAdapter.notifyDataSetChanged();
        }
        pageIndex += Contants.PAZE_SIZE;

    }

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadMsg() {
        if(pageIndex == 0) {
            newsAdapter.isShowFooter(false);
            newsAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.activity_main);
        Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (discroveryBeen != null) {
            discroveryBeen.clear();
        }
        discroveryNewsPresenter.LoadNews(0, pageIndex);
    }
}
