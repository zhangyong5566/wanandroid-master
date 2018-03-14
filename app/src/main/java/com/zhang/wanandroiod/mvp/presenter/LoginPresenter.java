package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.LoginContract;
import com.zhang.wanandroiod.mvp.model.LoginModel;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

/**
 * Created by Administrator on 2018/2/27.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.Model mModel;
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mModel = new LoginModel(this);
        mView = view;
    }

    @Override
    public void login(String username, String pwd) {
        mModel.login(username, pwd);
    }

    @Override
    public void loginSucc(UserInfo userInfo) {
        mView.loginSucc(userInfo);
    }

    @Override
    public void logingFail(Throwable ex) {
        mView.logingFail(ex);
    }
}
