package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.LoginContract;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/27.
 */

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter mPresenter;

    public LoginModel(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void login(String username, String pwd) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.login(username,pwd).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo userInfo) throws Exception {
                mPresenter.loginSucc(userInfo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.logingFail(throwable);
            }
        });
    }
}
