package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.NavigationContract;
import com.zhang.wanandroiod.mvp.model.NavigationModel;
import com.zhang.wanandroiod.mvp.model.bean.Navigation;

/**
 * Created by Administrator on 2018/3/2.
 */

public class NavigationPresenter implements NavigationContract.Presenter {
    private NavigationContract.Model mModel;
    private NavigationContract.View mView;

    public NavigationPresenter(NavigationContract.View mView) {
        mModel = new NavigationModel(this);
        this.mView = mView;
    }

    @Override
    public void loadNavigation() {
        mModel.loadNavigation();
    }

    @Override
    public void loadNavigationSucc(Navigation navigation) {
        mView.loadNavigationSucc(navigation);
    }

    @Override
    public void loadNavigationFail(Throwable ex) {
        mView.loadNavigationFail(ex);
    }
}
