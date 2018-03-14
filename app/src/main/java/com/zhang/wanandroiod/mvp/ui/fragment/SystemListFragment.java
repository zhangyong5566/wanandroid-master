package com.zhang.wanandroiod.mvp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.SystemArtcleTitleAdapter;
import com.zhang.wanandroiod.mvp.contract.ArtcleSystemTitleContract;
import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;
import com.zhang.wanandroiod.mvp.presenter.ArtcleSystemTitlePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 体系
 */
public class SystemListFragment extends Fragment implements ArtcleSystemTitleContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mInflate;
    private Unbinder mBind;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private ArtcleSystemTitlePresenter mArtcleSystemTitlePresenter;

    public SystemListFragment() {
        // Required empty public constructor
    }

    public static SystemListFragment newInstance(String param1, String param2) {
        SystemListFragment fragment = new SystemListFragment();
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
            mInflate = inflater.inflate(R.layout.fragment_system, container, false);

        }
        mBind = ButterKnife.bind(this, mInflate);

        mArtcleSystemTitlePresenter = new ArtcleSystemTitlePresenter(this);
        initData();

        return mInflate;
    }

    private void initData() {
        mArtcleSystemTitlePresenter.loadArtcleSystemTitle();
    }

    private void initPages(ArtcleSystemTitle artcleSystemTitle) {
        List<ArtcleSystemTitle.DataBean> dataBeans = artcleSystemTitle.getData();
        if (dataBeans.size() > 0) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            ArrayList<String> listTitle = new ArrayList<>();

            for (ArtcleSystemTitle.DataBean dataBean : dataBeans) {
                SystemArtcleFragment systemArtcleFragment = SystemArtcleFragment.newInstance(dataBean, null);
                fragments.add(systemArtcleFragment);
                listTitle.add(dataBean.getName());
                //为TabLayout添加tab名称
                tabLayout.addTab(tabLayout.newTab().setText(dataBean.getName()));
            }


            SystemArtcleTitleAdapter titleTabAdapter = new SystemArtcleTitleAdapter(getChildFragmentManager(), fragments, listTitle);

            //viewpager加载adapter
            viewPager.setAdapter(titleTabAdapter);
            tabLayout.setupWithViewPager(viewPager);

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }


    /**
     * 获取体系一级菜单成功
     *
     * @param artcleSystemTitle
     */
    @Override
    public void loadArtcleSystemTitleSucc(ArtcleSystemTitle artcleSystemTitle) {
        tabLayout.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        initPages(artcleSystemTitle);
    }

    /**
     * 获取体系一级菜单失败
     *
     * @param ex
     */
    @Override
    public void loadArtcleSystemTitleFail(Throwable ex) {
        tabLayout.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.retry_animation_view)
    public void retry(View view){

        mArtcleSystemTitlePresenter.loadArtcleSystemTitle();
    }
}
