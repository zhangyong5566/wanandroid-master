package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.ProjectClassContract;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassModel implements ProjectClassContract.Model {
    private ProjectClassContract.Presenter mPresenter;

    public ProjectClassModel(ProjectClassContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void loadProjectClass(int index,int cid) {
        PlayAndroidService playAndroidService = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        playAndroidService.loadProjectClass(index,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectClass>() {
                    @Override
                    public void accept(ProjectClass projectClass) throws Exception {
                        mPresenter.loadProjectClassSucc(projectClass);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mPresenter.loadProjectClassFail(throwable);
            }
        });
    }
}
