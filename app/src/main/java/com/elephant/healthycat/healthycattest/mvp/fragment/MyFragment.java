package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elephant.healthycat.healthycattest.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/23.
 */

public class MyFragment extends Fragment{
    private View view;

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
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("arge");
        TextView tv = (TextView)view.findViewById(R.id.mytv);
        tv.setText(agrs1);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
