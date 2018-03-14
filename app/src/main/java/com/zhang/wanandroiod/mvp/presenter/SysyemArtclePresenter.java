package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.SystemArtcleContract;
import com.zhang.wanandroiod.mvp.model.SystemArtcleModel;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

/**
 * Created by Administrator on 2018/3/2.
 */

public class SysyemArtclePresenter implements SystemArtcleContract.Presenter {
    private SystemArtcleContract.Model mModel;
    private SystemArtcleContract.View mView;

    public SysyemArtclePresenter(SystemArtcleContract.View view) {
        mView = view;
        mModel = new SystemArtcleModel(this);
    }

    @Override
    public void loadSystemArtcle(int page, int cid) {
        mModel.loadSystemArtcle(page, cid);
    }

    @Override
    public void loadSystemArtcleSucc(SystemArtcle systemArtcle) {
        mView.loadSystemArtcleSucc(systemArtcle);
    }

    @Override
    public void loadSystemArtcleFail(Throwable ex) {
        mView.loadSystemArtcleFail(ex);
    }
}
