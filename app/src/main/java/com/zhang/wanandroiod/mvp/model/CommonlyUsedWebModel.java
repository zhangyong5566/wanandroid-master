package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.CommonlyUsedWebContract;
import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/1.
 */

public class CommonlyUsedWebModel implements CommonlyUsedWebContract.Model {
    private CommonlyUsedWebContract.Presenter mPresenter;

    public CommonlyUsedWebModel(CommonlyUsedWebContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadCommonlyUsedWeb() {
        PlayAndroidService service = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        service.loadCommonlyUsedWeb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonlyUsedWeb>() {
                    @Override
                    public void accept(CommonlyUsedWeb commonlyUsedWeb) throws Exception {
                        mPresenter.loadCommonlyUsedWebSucc(commonlyUsedWeb);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadCommonlyUsedWebFail(throwable);
            }
        });

    }
}
