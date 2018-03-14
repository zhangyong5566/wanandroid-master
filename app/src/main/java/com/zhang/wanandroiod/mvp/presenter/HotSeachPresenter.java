package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.HotSeachContract;
import com.zhang.wanandroiod.mvp.model.HotSeachModel;
import com.zhang.wanandroiod.mvp.model.bean.HotSeachKey;

/**
 * Created by Administrator on 2018/3/14.
 */

public class HotSeachPresenter implements HotSeachContract.Presenter {
    private HotSeachContract.Model mModel;
    private HotSeachContract.View mView;

    public HotSeachPresenter(HotSeachContract.View view) {
        mModel = new HotSeachModel(this);
        mView = view;
    }

    @Override
    public void loadHotSeachKey() {
        mModel.loadHotSeachKey();

    }

    @Override
    public void loadHotSeachKeySucc(HotSeachKey hotSeachKey) {
        mView.loadHotSeachKeySucc(hotSeachKey);
    }

    @Override
    public void loadHotSeachKeyFail(Throwable ex) {
        mView.loadHotSeachKeyFail(ex);
    }
}
