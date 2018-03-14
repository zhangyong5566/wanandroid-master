package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.SeachContract;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/14.
 */

public class SeachModel implements SeachContract.Model {
    private SeachContract.Presenter mPresenter;

    public SeachModel(SeachContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadSeach(int index, String key) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadSeach(index,key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SystemArtcle>() {
            @Override
            public void accept(SystemArtcle systemArtcle) throws Exception {
                mPresenter.loadSeachSucc(systemArtcle);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadSeachFail(throwable);
            }
        });
    }
}
