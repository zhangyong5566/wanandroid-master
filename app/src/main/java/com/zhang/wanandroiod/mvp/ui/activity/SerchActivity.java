package com.zhang.wanandroiod.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.victor.loading.rotate.RotateLoading;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.adapter.LoadSystemArtcleAdapter;
import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.contract.HotSeachContract;
import com.zhang.wanandroiod.mvp.contract.RemoveCollectionContract;
import com.zhang.wanandroiod.mvp.contract.SeachContract;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;
import com.zhang.wanandroiod.mvp.model.bean.HotSeachKey;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;
import com.zhang.wanandroiod.mvp.presenter.AddCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.HotSeachPresenter;
import com.zhang.wanandroiod.mvp.presenter.RemoveCollectionPresenter;
import com.zhang.wanandroiod.mvp.presenter.SeachPresenter;
import com.zhang.wanandroiod.mvp.ui.base.BaseActivity;
import com.zhang.wanandroiod.views.DividerItemDecoration;
import com.zhang.wanandroiod.views.PlayAndroidToast;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SerchActivity extends BaseActivity implements HotSeachContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ry_seach)
    RecyclerView rySeach;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    @BindView(R.id.et_seach)
    EditText etSeach;
    @BindView(R.id.rl_hotseach)
    RelativeLayout rlHotseach;
    private Unbinder unbinder;
    private int mPage = 0;
    private boolean isLoadMor = false;
    private int mPageCount=0;
    private SeachPresenter mSeachPresenter;
    private LoadSystemArtcleAdapter mLoadSystemArtcleAdapter;
    private com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
                rlHotseach.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMor = true;
                        if (mPage == mPageCount) {
                            //数据全部加载完毕
                            mLoadSystemArtcleAdapter.loadMoreEnd();
                        } else {

                            mSeachPresenter.loadSeach(mPage,mSearchContext);
                            mLoadSystemArtcleAdapter.loadMoreComplete();
                        }
                    }

                }, 2000);

        }
    };
    private String mSearchContext;
    private long mTimeMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        etSeach.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SerchActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    if (System.currentTimeMillis()-mTimeMillis>500){
                        search();
                    }

                }
                return false;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mLoadSystemArtcleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String link = mLoadSystemArtcleAdapter.getData().get(position).getLink();
                Intent intent = new Intent(SerchActivity.this, BrowseArticleActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
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
    }

    private void search() {
        mSearchContext = etSeach.getText().toString().trim();
        isLoadMor = false;
        if (TextUtils.isEmpty(mSearchContext)) {
            PlayAndroidToast.warning("搜索框为空");
        } else {
            rotateloading.start();
            mTimeMillis = System.currentTimeMillis();
            // 调用搜索的API方法
            if (mSeachPresenter == null) {
                mSeachPresenter = new SeachPresenter(new SeachContract.View() {
                    @Override
                    public void loadSeachSucc(SystemArtcle systemArtcle) {
                        rotateloading.stop();
                        rlHotseach.setVisibility(View.GONE);
                        rySeach.setVisibility(View.VISIBLE);
                        mPage++;
                        mPageCount = systemArtcle.getData().getPageCount();
                        List<SystemArtcle.DataBean.DatasBean> datas = systemArtcle.getData().getDatas();
                        if (!isLoadMor) {

                            mLoadSystemArtcleAdapter.setNewData(datas);
                        } else {
                            mLoadSystemArtcleAdapter.addData(datas);
                        }
                        mLoadSystemArtcleAdapter.setNewData(datas);
                        if (datas.size()>19){
                            mLoadSystemArtcleAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, rySeach);
                        }
                    }

                    @Override
                    public void loadSeachFail(Throwable ex) {
                        rotateloading.stop();
                        rlHotseach.setVisibility(View.VISIBLE);
                        rySeach.setVisibility(View.GONE);

                    }
                });
            }

            mSeachPresenter.loadSeach(0, mSearchContext);
        }
    }

    private void initData() {
        HotSeachPresenter hotSeachPresenter = new HotSeachPresenter(this);
        hotSeachPresenter.loadHotSeachKey();
        mLoadSystemArtcleAdapter = new LoadSystemArtcleAdapter(R.layout.index_article_item, null);
        mLoadSystemArtcleAdapter.openLoadAnimation();
        rySeach.setAdapter(mLoadSystemArtcleAdapter);

    }

    private void initView() {

        rotateloading.start();
        rySeach.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rySeach.setItemAnimator(new DefaultItemAnimator());
        rySeach.addItemDecoration(new DividerItemDecoration(MyApplication.getApplictaion(), 1, 25, Color.DKGRAY));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    /**
     * 获取热门搜索成功
     *
     * @param hotSeachKey
     */
    @Override
    public void loadHotSeachKeySucc(final HotSeachKey hotSeachKey) {
        rotateloading.stop();
        flowlayout.setAdapter(new TagAdapter<HotSeachKey.DataBean>(hotSeachKey.getData()) {
            @Override
            public View getView(FlowLayout parent, int position, HotSeachKey.DataBean dataBean) {
                View inflate = View.inflate(MyApplication.getApplictaion(), R.layout.hot_tagflow_item, null);
                TextView tv_tag = inflate.findViewById(R.id.tv_tag);
                tv_tag.setText(dataBean.getName());
                return inflate;
            }
        });

        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String seachKey = hotSeachKey.getData().get(position).getName();
                etSeach.setText(seachKey);
                return false;
            }
        });
    }

    /**
     * 获取热门搜索失败
     *
     * @param ex
     */
    @Override
    public void loadHotSeachKeyFail(Throwable ex) {
        rotateloading.stop();
    }
}
