package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.NavigationContract;
import com.zhang.wanandroiod.mvp.model.bean.Navigation;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/2.
 */

public class NavigationModel implements NavigationContract.Model {
    private NavigationContract.Presenter mPresenter;

    public NavigationModel(NavigationContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void loadNavigation() {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadNavigation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Navigation>() {
                    @Override
                    public void accept(Navigation navigation) throws Exception {
                        mPresenter.loadNavigationSucc(navigation);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mPresenter.loadNavigationFail(throwable);
                    }
                });
    }
}
