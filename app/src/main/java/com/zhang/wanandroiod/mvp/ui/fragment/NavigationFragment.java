package com.zhang.wanandroiod.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.NavigationAdapter;
import com.zhang.wanandroiod.mvp.contract.NavigationContract;
import com.zhang.wanandroiod.mvp.model.bean.Navigation;
import com.zhang.wanandroiod.mvp.presenter.NavigationPresenter;
import com.zhang.wanandroiod.mvp.ui.activity.BrowseArticleActivity;
import com.zhang.wanandroiod.views.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导航
 */
public class NavigationFragment extends Fragment implements NavigationContract.View {    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mInflate;
    @BindView(R.id.layoutSwipeRefresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ry_navigation)
    RecyclerView ryNavigation;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private NavigationPresenter mNavigationPresenter;
    private NavigationAdapter mNavigationAdapter;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
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
            mInflate = inflater.inflate(R.layout.fragment_navigation, container, false);
        }
        ButterKnife.bind(this, mInflate);
        mNavigationPresenter = new NavigationPresenter(this);
        rotateloading.start();
        initView();
        initData();
        return mInflate;
    }

    private void initView() {
        layoutSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mNavigationAdapter.isLoading()) {

                    mNavigationPresenter.loadNavigation();
                }
            }
        });

        ryNavigation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ryNavigation.setItemAnimator(new DefaultItemAnimator());
        ryNavigation.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));
    }

    private void initData() {
        mNavigationAdapter = new NavigationAdapter(new NavigationAdapter.TagFlowLayoutOnItemClick() {

            @Override
            public void tagFlowOnItemClick(Navigation.DataBean.ArticlesBean articlesBean) {
                String link = articlesBean.getLink();
                Intent intent = new Intent(getActivity(), BrowseArticleActivity.class);
                intent.putExtra("link", link);
                getActivity().startActivity(intent);
            }
        }, R.layout.navigation_item, null);
        ryNavigation.setAdapter(mNavigationAdapter);
        mNavigationPresenter.loadNavigation();
    }

    /**
     * 获取导航数据成功
     *
     * @param navigation
     */
    @Override
    public void loadNavigationSucc(Navigation navigation) {
        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        layoutSwipeRefresh.setRefreshing(false);
        List<Navigation.DataBean> datas = navigation.getData();
            mNavigationAdapter.setNewData(datas);

    }

    /**
     * 获取导航数据失败
     *
     * @param ex
     */
    @Override
    public void loadNavigationFail(Throwable ex) {
        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
        layoutSwipeRefresh.setRefreshing(false);
    }

    @OnClick(R.id.retry_animation_view)
    public void retry(View view){
        mNavigationPresenter.loadNavigation();
    }
}
