package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.HotSeachContract;
import com.zhang.wanandroiod.mvp.model.bean.HotSeachKey;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/14.
 */

public class HotSeachModel implements HotSeachContract.Model {
    private HotSeachContract.Presenter mPresenter;

    public HotSeachModel(HotSeachContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadHotSeachKey() {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadHotSeachKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotSeachKey>() {
                    @Override
                    public void accept(HotSeachKey hotSeachKey) throws Exception {
                        mPresenter.loadHotSeachKeySucc(hotSeachKey);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mPresenter.loadHotSeachKeyFail(throwable);
                    }
                });

    }
}
