package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.ArtcleSystemTitleContract;
import com.zhang.wanandroiod.mvp.model.ArtcleSystemTitleModel;
import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ArtcleSystemTitlePresenter implements ArtcleSystemTitleContract.Presenter {
    private ArtcleSystemTitleContract.Model mModel;
    private ArtcleSystemTitleContract.View mView;

    public ArtcleSystemTitlePresenter(ArtcleSystemTitleContract.View view) {
        mView = view;
        mModel = new ArtcleSystemTitleModel(this);
    }

    @Override
    public void loadArtcleSystemTitle() {
        mModel.loadArtcleSystemTitle();
    }

    @Override
    public void loadArtcleSystemTitleSucc(ArtcleSystemTitle artcleSystemTitle) {
        mView.loadArtcleSystemTitleSucc(artcleSystemTitle);
    }

    @Override
    public void loadArtcleSystemTitleFail(Throwable ex) {
        mView.loadArtcleSystemTitleFail(ex);
    }
}
