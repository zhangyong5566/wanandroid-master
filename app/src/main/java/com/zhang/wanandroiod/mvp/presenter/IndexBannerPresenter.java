package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.IndexBannerContract;
import com.zhang.wanandroiod.mvp.model.IndexBannerModel;
import com.zhang.wanandroiod.mvp.model.bean.IndexBanner;

/**
 * Created by Administrator on 2018/2/28.
 */

public class IndexBannerPresenter implements IndexBannerContract.Presenter {
    private IndexBannerContract.Model mModel;
    private IndexBannerContract.View mView;

    public IndexBannerPresenter(IndexBannerContract.View view) {
        mModel = new IndexBannerModel(this);
        mView = view;
    }

    @Override
    public void loadBanner() {
        mModel.loadBanner();
    }

    @Override
    public void loadSucc(IndexBanner indexBanner) {
        mView.loadSucc(indexBanner);
    }

    @Override
    public void loadFail(Throwable ex) {
        mView.loadFail(ex);
    }
}
