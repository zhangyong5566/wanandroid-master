package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.RemoveCollectionContract;
import com.zhang.wanandroiod.mvp.model.RemoveCollectionModel;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;

/**
 * Created by Administrator on 2018/3/7.
 */

public class RemoveCollectionPresenter implements RemoveCollectionContract.Presenter {
    private RemoveCollectionContract.Model mModel;
    private RemoveCollectionContract.View mView;

    public RemoveCollectionPresenter(RemoveCollectionContract.View view) {
        mModel = new RemoveCollectionModel(this);
        mView = view;
    }

    @Override
    public void removeCollection(int index) {
        mModel.removeCollection(index);
    }

    @Override
    public void removeCollectionSucc(AddCollection collection) {
        mView.removeCollectionSucc(collection);
    }

    @Override
    public void removeCollectionFail(Throwable ex) {
        mView.removeCollectionFail(ex);
    }
}
