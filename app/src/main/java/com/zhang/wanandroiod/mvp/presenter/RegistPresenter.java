package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.RegistContract;
import com.zhang.wanandroiod.mvp.model.RegistModel;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

/**
 * Created by Administrator on 2018/3/5.
 */

public class RegistPresenter implements RegistContract.Presenter {
    private RegistContract.Model mModel;
    private RegistContract.View mView;

    public RegistPresenter(RegistContract.View view) {
        mModel = new RegistModel(this);
        mView = view;
    }

    @Override
    public void userRegist(String username, String pwd) {
        mModel.userRegist(username, pwd);

    }

    @Override
    public void userRegistSucc(UserInfo info) {
        mView.userRegistSucc(info);
    }

    @Override
    public void userRegistFail(Throwable ex) {
        mView.userRegistFail(ex);
    }
}
