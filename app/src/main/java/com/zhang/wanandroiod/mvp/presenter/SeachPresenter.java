package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.SeachContract;
import com.zhang.wanandroiod.mvp.model.SeachModel;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

/**
 * Created by Administrator on 2018/3/14.
 */

public class SeachPresenter implements SeachContract.Presenter {
    private SeachContract.Model mModel;
    private SeachContract.View mView;

    public SeachPresenter(SeachContract.View view) {
        mModel = new SeachModel(this);
        mView = view;
    }

    @Override
    public void loadSeach(int index, String key) {
        mModel.loadSeach(index, key);
    }

    @Override
    public void loadSeachSucc(SystemArtcle systemArtcle) {
        mView.loadSeachSucc(systemArtcle);
    }

    @Override
    public void loadSeachFail(Throwable ex) {
        mView.loadSeachFail(ex);
    }
}
