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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.LoadSystemArtcleAdapter;
import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.contract.RemoveCollectionContract;
import com.zhang.wanandroiod.mvp.contract.SystemArtcleContract;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;
import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;
import com.zhang.wanandroiod.mvp.presenter.AddCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.RemoveCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.SysyemArtclePresenter;
import com.zhang.wanandroiod.mvp.ui.activity.BrowseArticleActivity;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SystemArtcleFragment extends Fragment implements SystemArtcleContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATA = "dataBean";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArtcleSystemTitle.DataBean dataBean;
    private String mParam2;
    private View mInflate;
    private Unbinder mUnbind;
    @BindView(R.id.layoutSwipeRefresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ry_sys_artcle)
    RecyclerView rySysArtcle;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private int mPage = 0;
    private boolean isLoadMor = false;
    private SysyemArtclePresenter mSysyemArtclePresenter;
    private View mHeadView;
    private LoadSystemArtcleAdapter mLoadSystemArtcleAdapter;
    private com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
                rySysArtcle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMor = true;
                        if (mPage == mPageCount) {
                            //数据全部加载完毕
                            mLoadSystemArtcleAdapter.loadMoreEnd();
                        } else {

                            mSysyemArtclePresenter.loadSystemArtcle(mPage,mChildrenId);
                            mLoadSystemArtcleAdapter.loadMoreComplete();
                        }
                    }

                }, 2000);

        }
    };
    private int mPageCount=0;
    private TagAdapter<ArtcleSystemTitle.DataBean.ChildrenBean> mTagAdapter;
    private int mChildrenId=0;
    public SystemArtcleFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SystemArtcleFragment newInstance(ArtcleSystemTitle.DataBean dataBean, String param2) {
        SystemArtcleFragment fragment = new SystemArtcleFragment();
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
        if (mInflate==null){

            mInflate = inflater.inflate(R.layout.fragment_system_artcle, container, false);
        }
        mUnbind = ButterKnife.bind(this, mInflate);
        rotateloading.start();

        mSysyemArtclePresenter = new SysyemArtclePresenter(this);
        initView();
        initData();

        return mInflate;
    }

    private void initView() {
        layoutSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mLoadSystemArtcleAdapter.isLoading()){
                    isLoadMor = false;
                    mPage = 0;
                    mSysyemArtclePresenter.loadSystemArtcle(mPage,mChildrenId);
                }
            }
        });

        rySysArtcle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rySysArtcle.setItemAnimator(new DefaultItemAnimator());
        rySysArtcle.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1,25, Color.DKGRAY));
    }

    private void initData() {
        //请求首页文章
        if (dataBean!=null&&dataBean.getChildren().size()>0){
            mLoadSystemArtcleAdapter = new LoadSystemArtcleAdapter(R.layout.index_article_item, null);
            mLoadSystemArtcleAdapter.openLoadAnimation();

            //二级标签
            mHeadView = View.inflate(MyApplication.getApplictaion(), R.layout.system_artcle_head, null);
            TagFlowLayout tagFlowLayout = mHeadView.findViewById(R.id.flowlayout);
            if (mTagAdapter==null){
                mTagAdapter = new TagAdapter<ArtcleSystemTitle.DataBean.ChildrenBean>(dataBean.getChildren()) {

                    @Override
                    public View getView(FlowLayout parent, int position, ArtcleSystemTitle.DataBean.ChildrenBean childrenBean) {
                        View inflate = View.inflate(MyApplication.getApplictaion(), R.layout.system_artcle_tagflow_item, null);
                        TextView tv_tag = inflate.findViewById(R.id.tv_tag);
                        tv_tag.setText(dataBean.getChildren().get(position).getName());
                        return inflate;
                    }
                };
                mTagAdapter.setSelectedList(0);

            }

            tagFlowLayout.setAdapter(mTagAdapter);



            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    mChildrenId = dataBean.getChildren().get(position).getId();
                    mPage = 0;
                    rotateloading.start();
                    mSysyemArtclePresenter.loadSystemArtcle(mPage,mChildrenId);
                    return true;
                }
            });
            mLoadSystemArtcleAdapter.addHeaderView(mHeadView);

            rySysArtcle.setAdapter(mLoadSystemArtcleAdapter);

            mLoadSystemArtcleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String link = mLoadSystemArtcleAdapter.getData().get(position).getLink();
                    Intent intent = new Intent(getActivity(), BrowseArticleActivity.class);
                    intent.putExtra("link", link);
                    getActivity().startActivity(intent);
                }
            });


            mLoadSystemArtcleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    switch (view.getId()) {
                        case R.id.iv_collection:

                            if (!mLoadSystemArtcleAdapter.isLoading()){
                                if ( mLoadSystemArtcleAdapter.getData().get(position).isCollect()){    //取消收藏

                                    new RemoveCollectionPresenter(new RemoveCollectionContract.View() {
                                        @Override
                                        public void removeCollectionSucc(AddCollection collection) {
                                            if (collection.getErrorCode() == 0) {
                                                PlayAndroidToast.success("取消收藏成功");
                                                SystemArtcle.DataBean.DatasBean datasBean = mLoadSystemArtcleAdapter.getData().get(position);
                                                datasBean.setCollect(false);
                                                mLoadSystemArtcleAdapter.setData(position,datasBean);
                                            } else {
                                                PlayAndroidToast.error("取消收藏失败" + collection.getErrorMsg());
                                            }
                                        }

                                        @Override
                                        public void removeCollectionFail(Throwable ex) {
                                            PlayAndroidToast.error("取消收藏失败" + ex.getMessage());
                                        }
                                    }).removeCollection(mLoadSystemArtcleAdapter.getData().get(position).getId());
                                }else {          //收藏
                                    new AddCollectionPresenter(new AddCollectionContract.View() {
                                        @Override
                                        public void addCollectionSucc(AddCollection addCollection) {
                                            if (addCollection.getErrorCode() == 0) {
                                                PlayAndroidToast.success("收藏成功");
                                                SystemArtcle.DataBean.DatasBean datasBean = mLoadSystemArtcleAdapter.getData().get(position);
                                                datasBean.setCollect(true);
                                                mLoadSystemArtcleAdapter.setData(position,datasBean);
                                            } else {
                                                PlayAndroidToast.error("收藏失败" + addCollection.getErrorMsg());
                                            }

                                        }

                                        @Override
                                        public void addCollectionFail(Throwable ex) {
                                            PlayAndroidToast.error("收藏失败" + ex.getMessage());
                                        }
                                    }).addCollection(mLoadSystemArtcleAdapter.getData().get(position).getId());
                                }

                            }else {
                                PlayAndroidToast.warning("请等待数据加载完成");
                            }

                            break;
                        default:
                            break;
                    }

                }
            });
            mChildrenId = dataBean.getChildren().get(0).getId();
            mSysyemArtclePresenter.loadSystemArtcle(0,mChildrenId);
        }

    }


    /**
     * 获取体系下文章成功
     * @param systemArtcle
     */
    @Override
    public void loadSystemArtcleSucc(SystemArtcle systemArtcle) {
        rotateloading.stop();
        layoutSwipeRefresh.setRefreshing(false);
        layoutSwipeRefresh.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        final List<SystemArtcle.DataBean.DatasBean> datas = systemArtcle.getData().getDatas();
        mPage++;
        mPageCount = systemArtcle.getData().getPageCount();


            if (!isLoadMor) {

                mLoadSystemArtcleAdapter.setNewData(datas);
            } else {
                mLoadSystemArtcleAdapter.addData(datas);
            }

        if (datas.size()>19){
            mLoadSystemArtcleAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, rySysArtcle);
        }

    }

    /**
     * 获取体系下文章失败
     * @param ex
     */
    @Override
    public void loadSystemArtcleFail(Throwable ex) {
        layoutSwipeRefresh.setRefreshing(false);
        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.retry_animation_view)
    public void retry(View view){

        if (dataBean!=null){
            mChildrenId = dataBean.getChildren().get(0).getId();
            mSysyemArtclePresenter.loadSystemArtcle(0,mChildrenId);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbind.unbind();
    }
}
