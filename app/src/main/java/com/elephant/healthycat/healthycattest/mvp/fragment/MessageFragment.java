package com.elephant.healthycat.healthycattest.mvp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elephant.healthycat.healthycattest.R;
import com.elephant.healthycat.healthycattest.mvp.message.LoginPresenter;
import com.elephant.healthycat.healthycattest.mvp.message.LoginView;
import com.elephant.healthycat.healthycattest.util.BaseFragment;
import com.elephant.healthycat.healthycattest.util.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/23.
 */

public class MessageFragment extends BaseFragment implements LoginView{
    private View view;
    private LoginPresenter loginPresenter;
    private EditText nameEt,pwdEt;
    private ProgressBar progressBar;

    public static MessageFragment newInstance(String param) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arge", param);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    public MessageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_messagefragment_ll, container, false);
        ButterKnife.bind(this, view);
        nameEt= (EditText) view.findViewById(R.id.username);
        pwdEt= (EditText) view.findViewById(R.id.pwd);
        progressBar= (ProgressBar) view.findViewById(R.id.progressbar);

        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
        loginPresenter=new LoginPresenter(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void moveToNext() {


    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return nameEt.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return pwdEt.getText().toString().trim();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
