package com.zhang.wanandroiod.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.victor.loading.rotate.RotateLoading;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.LoadIndexArticleAdapter;
import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.contract.ArticleContract;
import com.zhang.wanandroiod.mvp.contract.CommonlyUsedWebContract;
import com.zhang.wanandroiod.mvp.contract.IndexBannerContract;
import com.zhang.wanandroiod.mvp.contract.RemoveCollectionContract;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;
import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;
import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;
import com.zhang.wanandroiod.mvp.model.bean.IndexBanner;
import com.zhang.wanandroiod.mvp.presenter.AddCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.ArticlePresenter;
import com.zhang.wanandroiod.mvp.presenter.CommonlyUsedWebPresenter;
import com.zhang.wanandroiod.mvp.presenter.IndexBannerPresenter;
import com.zhang.wanandroiod.mvp.presenter.RemoveCollectionPresenter;
import com.zhang.wanandroiod.mvp.ui.activity.BrowseArticleActivity;
import com.zhang.wanandroiod.utils.ListUtil;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class IndexFragment extends Fragment implements ArticleContract.View, IndexBannerContract.View, CommonlyUsedWebContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder mUnbind;
    private View mInflate;

    @BindView(R.id.layoutSwipeRefresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ry_index)
    RecyclerView ryIndex;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.retry_bt)
    RelativeLayout retryBt;
    private ArticlePresenter mArticlePresenter;
    private View mHeadView;
    private Banner mBanner;
    private IndexBannerPresenter mIndexBannerPresenter;
    private int mPage = 0;
    private boolean isLoadMor = false;
    private LoadIndexArticleAdapter mLoadIndexArticleAdapter;
    private BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            ryIndex.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isLoadMor = true;
                    if (mPage == mPageCount) {
                        //数据全部加载完毕
                        mLoadIndexArticleAdapter.loadMoreEnd();
                    } else {

                        mArticlePresenter.loadIndexArticle(mPage);
                        mLoadIndexArticleAdapter.loadMoreComplete();
                    }
                }

            }, 2000);
        }
    };
    private int mPageCount = 0;
    private CommonlyUsedWebPresenter mCommonlyUsedWebPresenter;
    private List<List<CommonlyUsedWeb.DataBean>> mSplitCommonList;

    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
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
            mInflate = inflater.inflate(R.layout.fragment_index, container, false);

        }
        mUnbind = ButterKnife.bind(this, mInflate);
        rotateloading.start();
        mArticlePresenter = new ArticlePresenter(this);
        mIndexBannerPresenter = new IndexBannerPresenter(this);
        mCommonlyUsedWebPresenter = new CommonlyUsedWebPresenter(this);
        initView();
        initData();
        return mInflate;
    }

    private void initView() {
        mHeadView = View.inflate(MyApplication.getApplictaion(), R.layout.index_head, null);
        mBanner = mHeadView.findViewById(R.id.banner);
        layoutSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMor = false;
                mPage = 0;
                mArticlePresenter.loadIndexArticle(mPage);
            }
        });

        ryIndex.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ryIndex.setItemAnimator(new DefaultItemAnimator());
        ryIndex.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));
    }

    private void initData() {
        mLoadIndexArticleAdapter = new LoadIndexArticleAdapter(null, new LoadIndexArticleAdapter.OnTagClickListener() {
            @Override
            public void onTagClickListener(CommonlyUsedWeb.DataBean bean) {
                String link = bean.getLink();
                Intent intent = new Intent(getActivity(), BrowseArticleActivity.class);
                intent.putExtra("link", link);
                getActivity().startActivity(intent);
            }
        });
        mLoadIndexArticleAdapter.addHeaderView(mHeadView);
        mLoadIndexArticleAdapter.openLoadAnimation();
        ryIndex.setAdapter(mLoadIndexArticleAdapter);
        mLoadIndexArticleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    default:
                        String link = mLoadIndexArticleAdapter.getData().get(position).getLink();
                        Intent intent = new Intent(getActivity(), BrowseArticleActivity.class);
                        intent.putExtra("link", link);
                        getActivity().startActivity(intent);
                        break;
                }

            }
        });


        mLoadIndexArticleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (position) {
                    case 0:
                        switch (view.getId()) {
                            case R.id.tv_change:

                                if (mSplitCommonList.size() > 0) {
                                    mLoadIndexArticleAdapter.setFlowDatas(mSplitCommonList.get(new Random().nextInt(mSplitCommonList.size())));
                                    IndexArticle.DataBean.DatasBean datasBean = new IndexArticle.DataBean.DatasBean();
                                    datasBean.setItemType(0);
                                    mLoadIndexArticleAdapter.setData(0,datasBean);
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        switch (view.getId()) {
                            case R.id.iv_collection:

                                if (!mLoadIndexArticleAdapter.isLoading()){
                                    if ( mLoadIndexArticleAdapter.getData().get(position).isCollect()){    //取消收藏

                                        new RemoveCollectionPresenter(new RemoveCollectionContract.View() {
                                            @Override
                                            public void removeCollectionSucc(AddCollection collection) {
                                                if (collection.getErrorCode() == 0) {
                                                    PlayAndroidToast.success("取消收藏成功");
                                                    IndexArticle.DataBean.DatasBean datasBean =mLoadIndexArticleAdapter.getData().get(position);
                                                    datasBean.setCollect(false);
                                                    mLoadIndexArticleAdapter.setData(position,datasBean);
                                                } else {
                                                    PlayAndroidToast.error("取消收藏失败" + collection.getErrorMsg());
                                                }
                                            }

                                            @Override
                                            public void removeCollectionFail(Throwable ex) {
                                                PlayAndroidToast.error("取消收藏失败" + ex.getMessage());
                                            }
                                        }).removeCollection(mLoadIndexArticleAdapter.getData().get(position).getId());
                                    }else {          //收藏
                                        new AddCollectionPresenter(new AddCollectionContract.View() {
                                            @Override
                                            public void addCollectionSucc(AddCollection addCollection) {
                                                if (addCollection.getErrorCode() == 0) {
                                                    PlayAndroidToast.success("收藏成功");
                                                    IndexArticle.DataBean.DatasBean datasBean = mLoadIndexArticleAdapter.getData().get(position);
                                                    datasBean.setCollect(true);
                                                    mLoadIndexArticleAdapter.setData(position,datasBean);
                                                } else {
                                                    PlayAndroidToast.error("收藏失败" + addCollection.getErrorMsg());
                                                }

                                            }

                                            @Override
                                            public void addCollectionFail(Throwable ex) {
                                                PlayAndroidToast.error("收藏失败" + ex.getMessage());
                                            }
                                        }).addCollection(mLoadIndexArticleAdapter.getData().get(position).getId());
                                    }

                                }else {
                                    PlayAndroidToast.warning("请等待数据加载完成");
                                }

                                break;
                            default:
                                break;
                        }
                        break;
                }
            }
        });


        //请求首页文章
        mArticlePresenter.loadIndexArticle(mPage);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbind.unbind();
    }

    /**
     * 首页文章请求成功
     *
     * @param indexArticle
     */
    @Override
    public void loadIndexArticleSucc(IndexArticle indexArticle) {

        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.VISIBLE);
        retryBt.setVisibility(View.GONE);
        final List<IndexArticle.DataBean.DatasBean> datas = new ArrayList<>();

        if (!isLoadMor) {
            //请求banner
            mIndexBannerPresenter.loadBanner();
            //请求常用网站
            mCommonlyUsedWebPresenter.loadCommonlyUsedWeb();
            //把第一条条目类型改变
            IndexArticle.DataBean.DatasBean datasBean = new IndexArticle.DataBean.DatasBean();
            datasBean.setItemType(0);
            datas.add(datasBean);
        }
        datas.addAll(indexArticle.getData().getDatas());
        mPage++;
        layoutSwipeRefresh.setRefreshing(false);
        mPageCount = indexArticle.getData().getPageCount();



            if (!isLoadMor) {

                mLoadIndexArticleAdapter.setNewData(datas);
            } else {
                mLoadIndexArticleAdapter.addData(datas);
            }

        if (datas.size() > 19) {
            mLoadIndexArticleAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, ryIndex);
        }
    }


    /**
     * 首页文章请求失败
     *
     * @param ex
     */
    @Override
    public void loadIndexArticleFail(Throwable ex) {
        rotateloading.stop();
        layoutSwipeRefresh.setVisibility(View.GONE);
        retryBt.setVisibility(View.VISIBLE);
        layoutSwipeRefresh.setRefreshing(false);
        if (mLoadIndexArticleAdapter != null) {

            mLoadIndexArticleAdapter.loadMoreFail();
        }
    }

    /**
     * 请求banner成功
     *
     * @param indexBanner
     */
    @Override
    public void loadSucc(IndexBanner indexBanner) {

        List<IndexBanner.DataBean> bannerData = indexBanner.getData();
        if (bannerData.size() > 0) {
            ArrayList<String> images = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();
            for (IndexBanner.DataBean bannerDatum : bannerData) {
                images.add(bannerDatum.getImagePath());
                titles.add(bannerDatum.getTitle());

            }
            //设置banner样式
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片集合
            mBanner.setImages(images);
            //设置图片加载器
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    //用fresco加载图片
                    Uri uri = Uri.parse((String) path);
                    imageView.setImageURI(uri);
                }

                @Override
                public ImageView createImageView(Context context) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                    return simpleDraweeView;
                }
            });

            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            mBanner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            //设置轮播时间
            mBanner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();

        }

    }

    /**
     * 请求banner失败
     *
     * @param ex
     */
    @Override
    public void loadFail(Throwable ex) {

    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    /**
     * 获取常用网站成功
     *
     * @param commonlyUsedWeb
     */
    @Override
    public void loadCommonlyUsedWebSucc(CommonlyUsedWeb commonlyUsedWeb) {
        List<CommonlyUsedWeb.DataBean> datas = commonlyUsedWeb.getData();
        mSplitCommonList = ListUtil.split(datas, 10);
        IndexArticle.DataBean.DatasBean datasBean = new IndexArticle.DataBean.DatasBean();
        datasBean.setItemType(0);
        if (mLoadIndexArticleAdapter != null) {
            mLoadIndexArticleAdapter.setFlowDatas(mSplitCommonList.get(0));
            mLoadIndexArticleAdapter.setData(0, datasBean);
        }

    }

    /**
     * 获取常用网失败
     *
     * @param ex
     */
    @Override
    public void loadCommonlyUsedWebFail(Throwable ex) {

    }

    @OnClick(R.id.retry_animation_view)
    public void retry(View view) {
        mArticlePresenter.loadIndexArticle(mPage);
    }
}
