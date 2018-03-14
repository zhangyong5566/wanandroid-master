package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.ProjectClassTitleContract;
import com.zhang.wanandroiod.mvp.model.ProjectClassTitleModel;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassTitlePresenter implements ProjectClassTitleContract.Presenter {
    private ProjectClassTitleContract.Model mModel;
    private ProjectClassTitleContract.View mView;

    public ProjectClassTitlePresenter(ProjectClassTitleContract.View mView) {
        mModel = new ProjectClassTitleModel(this);
        this.mView = mView;
    }

    @Override
    public void loadProjectClassTitle() {
        mModel.loadProjectClassTitle();
    }

    @Override
    public void loadProjectClassTitleSucc(ProjectClassTitle mProjectClassTitle) {
        mView.loadProjectClassTitleSucc(mProjectClassTitle);
    }

    @Override
    public void loadProjectClassTitleFail(Throwable ex) {
        mView.loadProjectClassTitleFail(ex);
    }
}
