package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.ProjectClassTitleContract;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassTitleModel implements ProjectClassTitleContract.Model {
    private ProjectClassTitleContract.Presenter mPresenter;

    public ProjectClassTitleModel(ProjectClassTitleContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void loadProjectClassTitle() {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadProjectClassTitle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectClassTitle>() {
            @Override
            public void accept(ProjectClassTitle projectClassTitle) throws Exception {
                mPresenter.loadProjectClassTitleSucc(projectClassTitle);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadProjectClassTitleFail(throwable);
            }
        });
    }
}
