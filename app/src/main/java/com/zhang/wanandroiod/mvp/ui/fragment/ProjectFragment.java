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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.ProjectClassAdapter;
import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.contract.ProjectClassContract;
import com.zhang.wanandroiod.mvp.contract.RemoveCollectionContract;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;
import com.zhang.wanandroiod.mvp.presenter.AddCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.ProjectClassPresenter;
import com.zhang.wanandroiod.mvp.presenter.RemoveCollectionPresenter;
import com.zhang.wanandroiod.mvp.ui.activity.BrowseArticleActivity;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 */
public class ProjectFragment extends Fragment implements ProjectClassContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATA = "dataBean";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ProjectClassTitle.DataBean dataBean;
    private String mParam2;
    private View mInflate;
    @BindView(R.id.layoutSwipeRefresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ry_project)
    RecyclerView ryProject;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private int mPage = 1;
    private boolean isLoadMor = false;
    private int mPageCount=0;
    private com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
                ryProject.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMor = true;
                        if (mPage == mPageCount) {
                            //数据全部加载完毕
                            mProjectClassAdapter.loadMoreEnd();
                        } else {

                            mProjectClassPresenter.loadProjectClass(mPage,mChildrenId);
                            mProjectClassAdapter.loadMoreComplete();
                        }
                    }

                }, 2000);

        }
    };
    private int mChildrenId=0;
    private ProjectClassPresenter mProjectClassPresenter;
    private ProjectClassAdapter mProjectClassAdapter;
    private Unbinder Ubind;

    public ProjectFragment() {
        // Required empty public constructor
    }

    public static ProjectFragment newInstance(ProjectClassTitle.DataBean dataBean, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DATA, dataBean);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBean = getArguments().getParcelable(ARG_DATA);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {

            mInflate = inflater.inflate(R.layout.fragment_project, container, false);
        }
        Ubind = ButterKnife.bind(this, mInflate);
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
                if (!mProjectClassAdapter.isLoading()) {
                    isLoadMor = false;
                    mPage = 0;
                    mProjectClassPresenter.loadProjectClass(mPage,mChildrenId);
                }
            }
        });

        ryProject.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ryProject.setItemAnimator(new DefaultItemAnimator());
        ryProject.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));

    }

    private void initData() {
        if (dataBean != null) {
            mProjectClassAdapter = new ProjectClassAdapter(R.layout.project_class_item,null);
            mProjectClassAdapter.openLoadAnimation();
            ryProject.setAdapter(mProjectClassAdapter);

            mProjectClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String link = mProjectClassAdapter.getData().get(position).getLink();
                    Intent intent = new Intent(getActivity(), BrowseArticleActivity.class);
                    intent.putExtra("link", link);
                    getActivity().startActivity(intent);
                }
            });

            mProjectClassAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    if (!mProjectClassAdapter.isLoading()){
                        if ( mProjectClassAdapter.getData().get(position).isCollect()){    //取消收藏

                            new RemoveCollectionPresenter(new RemoveCollectionContract.View() {
                                @Override
                                public void removeCollectionSucc(AddCollection collection) {
                                    if (collection.getErrorCode() == 0) {
                                        PlayAndroidToast.success("取消收藏成功");
                                        ProjectClass.DataBean.DatasBean datasBean = mProjectClassAdapter.getData().get(position);
                                        datasBean.setCollect(false);
                                        mProjectClassAdapter.setData(position,datasBean);
                                    } else {
                                        PlayAndroidToast.error("取消收藏失败" + collection.getErrorMsg());
                                    }
                                }

                                @Override
                                public void removeCollectionFail(Throwable ex) {
                                    PlayAndroidToast.error("取消收藏失败" + ex.getMessage());
                                }
                            }).removeCollection(mProjectClassAdapter.getData().get(position).getId());
                        }else {          //收藏
                            new AddCollectionPresenter(new AddCollectionContract.View() {
                                @Override
                                public void addCollectionSucc(AddCollection addCollection) {
                                    if (addCollection.getErrorCode() == 0) {
                                        PlayAndroidToast.success("收藏成功");
                                        ProjectClass.DataBean.DatasBean datasBean = mProjectClassAdapter.getData().get(position);
                                        datasBean.setCollect(true);
                                        mProjectClassAdapter.setData(position,datasBean);
                                    } else {
                                        PlayAndroidToast.error("收藏失败" + addCollection.getErrorMsg());
                                    }

                                }

                                @Override
                                public void addCollectionFail(Throwable ex) {
                                    PlayAndroidToast.error("收藏失败" + ex.getMessage());
                                }
                            }).addCollection(mProjectClassAdapter.getData().get(position).getId());
                        }

                    }else {
                        PlayAndroidToast.warning("请等待数据加载完成");
                    }

                }
            });


            //请求首页文章
            mChildrenId = dataBean.getId();
            mProjectClassPresenter = new ProjectClassPresenter(this);
            mProjectClassPresenter.loadProjectClass(1,mChildrenId);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Ubind.unbind();
    }


    /**
     * 获取当前项目类型下的条目成功
     *
     * @param projectClass
     */
    @Override
    public void loadProjectClassSucc(ProjectClass projectClass) {
        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);
        layoutSwipeRefresh.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        final List<ProjectClass.DataBean.DatasBean> datas = projectClass.getData().getDatas();
        mPageCount = projectClass.getData().getPageCount();
        mPage++;
            if (!isLoadMor) {

                mProjectClassAdapter.setNewData(datas);
            } else {
                mProjectClassAdapter.addData(datas);
            }
        if (datas.size()>19){
            mProjectClassAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, ryProject);
        }
    }

    /**
     * 获取当前项目类型下的条目失败
     *
     * @param ex
     */
    @Override
    public void loadProjectClassFail(Throwable ex) {

        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);
        layoutSwipeRefresh.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.retry_animation_view)
    public void retry(View view){

        mProjectClassPresenter.loadProjectClass(1,mChildrenId);
    }
}
