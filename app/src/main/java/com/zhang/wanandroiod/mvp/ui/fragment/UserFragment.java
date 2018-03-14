package com.zhang.wanandroiod.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.UserAdaper;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;
import com.zhang.wanandroiod.mvp.ui.activity.CollectionActivity;
import com.zhang.wanandroiod.mvp.ui.activity.LoginActivity;
import com.zhang.wanandroiod.utils.DeviceUtil;
import com.zhang.wanandroiod.utils.SPUtil;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class UserFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View mInflate;

    @BindView(R.id.main_sdv)
    SimpleDraweeView userSdv;

    @BindView(R.id.ry_user)
    RecyclerView ryUser;

    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.bt_login)
    Button btLogin;
    private Unbinder mBind;
    private boolean mIsLogin;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {

            mInflate = inflater.inflate(R.layout.fragment_user, container, false);
        }
        mBind = ButterKnife.bind(this, mInflate);
        initView();
        initData();
        return mInflate;
    }


    private void initData() {
        mIsLogin = SPUtil.getBoolean(MyApplication.getApplictaion(), "isLogin");
        if (mIsLogin) {
            tvUsername.setText("亲爱的：" + SPUtil.getString(MyApplication.getApplictaion(), "username"));
            btLogin.setText("退出登录");
        }

    }

    private void initView() {
        ryUser.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ryUser.setItemAnimator(new DefaultItemAnimator());
        ryUser.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));
        ArrayList<String> titles = new ArrayList<>();
        titles.add("我的收藏");
        titles.add("意见反馈");
        titles.add("关于");
        titles.add("版本        " + DeviceUtil.getAppVersionName(MyApplication.getApplictaion()));
        UserAdaper userAdaper = new UserAdaper(R.layout.user_item, titles);
        userAdaper.openLoadAnimation();
        ryUser.setAdapter(userAdaper);
        userAdaper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    if (mIsLogin) {

                        getActivity().startActivity(new Intent(getActivity(), CollectionActivity.class));
                    } else {
                        PlayAndroidToast.warning("请先登录");
                    }
                }
            }
        });
    }

    @OnClick(R.id.bt_login)
    public void onClick(View view) {
        if (SPUtil.getBoolean(MyApplication.getApplictaion(), "isLogin")) {
            SPUtil.clearPreference(MyApplication.getApplictaion(), "isLogin", null);
            tvUsername.setText("未登录");
            PlayAndroidToast.success("退出登录成功");
            btLogin.setText("登录");
            mIsLogin = false;
            //清除cookie
            SharedPreferences sp = MyApplication.getApplictaion().getSharedPreferences("cookies_prefs", Context.MODE_PRIVATE);
            sp.edit().clear().commit();
        } else {
            getActivity().startActivityFromFragment(this, new Intent(getActivity(), LoginActivity.class), 600);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            Bundle bundle = data.getBundleExtra("regist");
            UserInfo.DataBean userInfo = bundle.getParcelable("registinfo");
            tvUsername.setText("亲爱的：" + userInfo.getUsername());
            btLogin.setText("退出登录");
            SPUtil.putString(MyApplication.getApplictaion(), "username", userInfo.getUsername());
            SPUtil.putString(MyApplication.getApplictaion(), "pwd", userInfo.getPassword());
            SPUtil.putBoolean(MyApplication.getApplictaion(), "isLogin", true);
            mIsLogin = true;
        } else if (resultCode == 0) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

}
