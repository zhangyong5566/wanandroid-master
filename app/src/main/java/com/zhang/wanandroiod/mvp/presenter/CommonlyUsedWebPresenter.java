package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.CommonlyUsedWebContract;
import com.zhang.wanandroiod.mvp.model.CommonlyUsedWebModel;
import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;

/**
 * Created by Administrator on 2018/3/1.
 */

public class CommonlyUsedWebPresenter implements CommonlyUsedWebContract.Presenter {
    private CommonlyUsedWebContract.Model mModel;
    private CommonlyUsedWebContract.View mView;

    public CommonlyUsedWebPresenter(CommonlyUsedWebContract.View view) {
        mModel = new CommonlyUsedWebModel(this);
        mView = view;
    }

    @Override
    public void loadCommonlyUsedWeb() {
        mModel.loadCommonlyUsedWeb();
    }

    @Override
    public void loadCommonlyUsedWebSucc(CommonlyUsedWeb commonlyUsedWeb) {
        mView.loadCommonlyUsedWebSucc(commonlyUsedWeb);
    }

    @Override
    public void loadCommonlyUsedWebFail(Throwable ex) {
        mView.loadCommonlyUsedWebFail(ex);
    }
}
