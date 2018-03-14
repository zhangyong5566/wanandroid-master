package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/7.
 */

public class AddCollectionModel implements AddCollectionContract.Model {
    private AddCollectionContract.Presenter mPresenter;

    public AddCollectionModel(AddCollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void addCollection(int id) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.addCollection(id,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCollection>() {
                    @Override
                    public void accept(AddCollection addCollection) throws Exception {
                        mPresenter.addCollectionSucc(addCollection);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.addCollectionFail(throwable);
            }
        });
    }
}
