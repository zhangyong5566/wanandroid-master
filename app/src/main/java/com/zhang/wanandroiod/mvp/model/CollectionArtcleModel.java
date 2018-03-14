package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.CollectionArtcleContract;
import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CollectionArtcleModel implements CollectionArtcleContract.Model {
    private CollectionArtcleContract.Presenter mPresenter;

    public CollectionArtcleModel(CollectionArtcleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadCollectionArtcle(int index) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadCollectionArtcle(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CollectionArtcle>() {
                    @Override
                    public void accept(CollectionArtcle collectionArtcle) throws Exception {
                        mPresenter.loadCollectionArtcleSucc(collectionArtcle);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadCollectionArtcleFail(throwable);
            }
        });
    }
}
