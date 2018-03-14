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
import com.zhang.wanandroiod.mvp.contract.ProjectClassTitleContract;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;
import com.zhang.wanandroiod.mvp.presenter.ProjectClassTitlePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目列表
 */
public class ProjectListFragment extends Fragment implements ProjectClassTitleContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private String mParam1;
    private String mParam2;
    private View mInflate;
    private ProjectClassTitlePresenter mProjectClassTitlePresenter;
    private Unbinder mUbind;


    public ProjectListFragment() {
        // Required empty public constructor
    }

    public static ProjectListFragment newInstance(String param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
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
            mInflate = inflater.inflate(R.layout.fragment_project_list, container, false);

        }
        mUbind = ButterKnife.bind(this, mInflate);
        mProjectClassTitlePresenter = new ProjectClassTitlePresenter(this);
        initView();
        initData();
        return mInflate;
    }

    private void initView() {

    }

    private void initData() {
        mProjectClassTitlePresenter.loadProjectClassTitle();
    }

    /**
     * 获取项目数据成功
     *
     * @param mProjectClassTitle
     */
    @Override
    public void loadProjectClassTitleSucc(ProjectClassTitle mProjectClassTitle) {
        tabLayout.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        initPage(mProjectClassTitle);
    }

    private void initPage(ProjectClassTitle mProjectClassTitle) {
        List<ProjectClassTitle.DataBean> dataBeans = mProjectClassTitle.getData();
        if (dataBeans.size() > 0) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            ArrayList<String> listTitle = new ArrayList<>();

            for (ProjectClassTitle.DataBean dataBean : dataBeans) {

                ProjectFragment projectFragment = ProjectFragment.newInstance(dataBean, null);
                fragments.add(projectFragment);
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

    /**
     * 获取项目数据失败
     *
     * @param ex
     */
    @Override
    public void loadProjectClassTitleFail(Throwable ex) {
        tabLayout.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUbind.unbind();
    }


    @OnClick(R.id.retry_animation_view)
    public void retry(View view){
        mProjectClassTitlePresenter.loadProjectClassTitle();
    }
}
