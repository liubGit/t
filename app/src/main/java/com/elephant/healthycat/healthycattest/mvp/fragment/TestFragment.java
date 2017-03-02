package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.util.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/25.
 */

public class TestFragment extends BaseFragment {

    public TestFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_my_testfragment_item,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
