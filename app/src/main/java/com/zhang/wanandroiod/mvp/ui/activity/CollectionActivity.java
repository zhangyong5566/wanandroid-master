package com.zhang.wanandroiod.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.CollectionAdapter;
import com.zhang.wanandroiod.mvp.contract.CollectionArtcleContract;
import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;
import com.zhang.wanandroiod.mvp.presenter.CollectionArtclePresenter;
import com.zhang.wanandroiod.mvp.ui.base.BaseActivity;
import com.zhang.wanandroiod.utils.SPUtil;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收藏
 */
public class CollectionActivity extends BaseActivity implements CollectionArtcleContract.View {

    @BindView(R.id.layoutSwipeRefresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ry_collection)
    RecyclerView ryCollection;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private Unbinder mUnbind;
    private int mPage = 0;
    private boolean isLoadMor = false;
    private int mPageCount = 0;
    private CollectionArtclePresenter mCollectionArtclePresenter;

    private BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            ryCollection.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMor = true;
                    if (mPage == mPageCount) {
                        //数据全部加载完毕
                        mCollectionAdapter.loadMoreEnd();
                    } else {

                        mCollectionArtclePresenter.loadCollectionArtcle(mPage);
                        mCollectionAdapter.loadMoreComplete();
                    }
                }

            }, 2000);
        }
    };
    private CollectionAdapter mCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        mUnbind = ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        layoutSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMor = false;
                mPage = 0;
                mCollectionArtclePresenter.loadCollectionArtcle(mPage);
            }
        });

        ryCollection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ryCollection.setItemAnimator(new DefaultItemAnimator());
        ryCollection.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));

    }

    private void initData() {
        rotateloading.start();
        mCollectionAdapter = new CollectionAdapter(R.layout.index_article_item, null);
        mCollectionAdapter.openLoadAnimation();
        ryCollection.setAdapter(mCollectionAdapter);
        mCollectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    default:
                        String link = mCollectionAdapter.getData().get(position).getLink();
                        Intent intent = new Intent(CollectionActivity.this, BrowseArticleActivity.class);
                        intent.putExtra("link", link);
                        startActivity(intent);
                        break;
                }

            }
        });

        mCollectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mCollectionArtclePresenter = new CollectionArtclePresenter(this);
        mCollectionArtclePresenter.loadCollectionArtcle(mPage);
    }

    /**
     * 获取收藏列表成功
     *
     * @param collectionArtcle
     */
    @Override
    public void loadCollectionArtcleSucc(CollectionArtcle collectionArtcle) {
        rotateloading.stop();

        layoutSwipeRefresh.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        if (collectionArtcle.getErrorCode() == -1) {
            if (SPUtil.getBoolean(MyApplication.getApplictaion(),"isLogin")){
                PlayAndroidToast.error("信息已过期请重新登录");
//                sendBroadcast();

            }else {
                PlayAndroidToast.error("请先登录");
            }

            return;
        }
        final List<CollectionArtcle.DataBean.DatasBean> datas = collectionArtcle.getData().getDatas();
        mPage++;
        layoutSwipeRefresh.setRefreshing(false);
        mPageCount = collectionArtcle.getData().getPageCount();



            if (!isLoadMor) {

                mCollectionAdapter.setNewData(datas);
            } else {
                mCollectionAdapter.addData(datas);
            }
        if (datas.size() > 19) {
            mCollectionAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, ryCollection);
        }

    }


    /**
     * 获取收藏列表失败
     *
     * @param ex
     */
    @Override
    public void loadCollectionArtcleFail(Throwable ex) {
        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
        layoutSwipeRefresh.setRefreshing(false);
        if (mCollectionAdapter != null) {

            mCollectionAdapter.loadMoreFail();
        }
    }

    @OnClick({R.id.retry_animation_view, R.id.iv_back})
    public void retry(View view) {
        switch (view.getId()) {
            case R.id.retry_animation_view:
                mCollectionArtclePresenter.loadCollectionArtcle(mPage);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
    }
}
