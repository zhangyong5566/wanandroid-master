package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.AddCollectionContract;
import com.zhang.wanandroiod.mvp.model.AddCollectionModel;
import com.zhang.wanandroiod.mvp.model.bean.AddCollection;

/**
 * Created by Administrator on 2018/3/7.
 */

public class AddCollectionPresenter implements AddCollectionContract.Presenter {
    private AddCollectionContract.Model mModel;
    private AddCollectionContract.View mView;

    public AddCollectionPresenter(AddCollectionContract.View view) {
        mModel = new AddCollectionModel(this);
        mView = view;
    }

    @Override
    public void addCollection(int id) {
        mModel.addCollection(id);
    }

    @Override
    public void addCollectionSucc(AddCollection addCollection) {
        mView.addCollectionSucc(addCollection);
    }

    @Override
    public void addCollectionFail(Throwable ex) {
        mView.addCollectionFail(ex);
    }
}
