package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.CollectionArtcleContract;
import com.zhang.wanandroiod.mvp.model.CollectionArtcleModel;
import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CollectionArtclePresenter implements CollectionArtcleContract.Presenter {
    private CollectionArtcleContract.Model mModel;
    private CollectionArtcleContract.View mView;

    public CollectionArtclePresenter(CollectionArtcleContract.View view) {
        mModel = new CollectionArtcleModel(this);
        mView = view;
    }

    @Override
    public void loadCollectionArtcle(int index) {
        mModel.loadCollectionArtcle(index);
    }

    @Override
    public void loadCollectionArtcleSucc(CollectionArtcle collectionArtcle) {
        mView.loadCollectionArtcleSucc(collectionArtcle);
    }

    @Override
    public void loadCollectionArtcleFail(Throwable ex) {
        mView.loadCollectionArtcleFail(ex);
    }
}
