package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.SystemArtcleContract;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/2.
 */

public class SystemArtcleModel implements SystemArtcleContract.Model {
    private SystemArtcleContract.Presenter mPresenter;

    public SystemArtcleModel(SystemArtcleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadSystemArtcle(int page, int cid) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadSystemArtcle(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SystemArtcle>() {
                    @Override
                    public void accept(SystemArtcle systemArtcle) throws Exception {
                        mPresenter.loadSystemArtcleSucc(systemArtcle);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadSystemArtcleFail(throwable);
            }
        });
    }

}
