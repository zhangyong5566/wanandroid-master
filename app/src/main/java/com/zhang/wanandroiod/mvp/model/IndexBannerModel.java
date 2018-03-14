package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.IndexBannerContract;
import com.zhang.wanandroiod.mvp.model.bean.IndexBanner;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/28.
 */

public class IndexBannerModel implements IndexBannerContract.Model {
    private IndexBannerContract.Presenter mPresenter;

    public IndexBannerModel(IndexBannerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadBanner() {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadIndexBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IndexBanner>() {
                    @Override
                    public void accept(IndexBanner indexBanner) throws Exception {
                        mPresenter.loadSucc(indexBanner);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mPresenter.loadFail(throwable);
                    }
                });
    }
}
