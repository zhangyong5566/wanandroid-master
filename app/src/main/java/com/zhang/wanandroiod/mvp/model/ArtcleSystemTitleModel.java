package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.ArtcleSystemTitleContract;
import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ArtcleSystemTitleModel implements ArtcleSystemTitleContract.Model {
    private ArtcleSystemTitleContract.Presenter mPresenter;

    public ArtcleSystemTitleModel(ArtcleSystemTitleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadArtcleSystemTitle() {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadArtcleSystemTitle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArtcleSystemTitle>() {
                    @Override
                    public void accept(ArtcleSystemTitle artcleSystemTitle) throws Exception {
                        mPresenter.loadArtcleSystemTitleSucc(artcleSystemTitle);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mPresenter.loadArtcleSystemTitleFail(throwable);
                    }
                });
    }
}
