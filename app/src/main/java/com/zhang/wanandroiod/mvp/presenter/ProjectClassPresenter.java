package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.ProjectClassContract;
import com.zhang.wanandroiod.mvp.model.ProjectClassModel;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassPresenter implements ProjectClassContract.Presenter {
    private ProjectClassContract.Model mModel;
    private ProjectClassContract.View mView;

    public ProjectClassPresenter(ProjectClassContract.View mView) {
        mModel = new ProjectClassModel(this);
        this.mView = mView;
    }


    @Override
    public void loadProjectClass(int index, int cid) {
        mModel.loadProjectClass(index, cid);
    }

    @Override
    public void loadProjectClassSucc(ProjectClass projectClass) {
        mView.loadProjectClassSucc(projectClass);
    }

    @Override
    public void loadProjectClassFail(Throwable ex) {
        mView.loadProjectClassFail(ex);
    }
}
