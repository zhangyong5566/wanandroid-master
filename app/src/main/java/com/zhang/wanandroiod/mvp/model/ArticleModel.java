package com.zhang.wanandroiod.mvp.model;

import com.zhang.wanandroiod.http.Api;
import com.zhang.wanandroiod.http.PlayAndroidHttpFactory;
import com.zhang.wanandroiod.http.service.PlayAndroidService;
import com.zhang.wanandroiod.mvp.contract.ArticleContract;
import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/2/27.
 */

public class ArticleModel implements ArticleContract.Model {
    private ArticleContract.Presenter mPresenter;

    public ArticleModel(ArticleContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void loadIndexArticle( int index) {

        PlayAndroidService service = PlayAndroidHttpFactory.getInstence().createService(PlayAndroidService.class, Api.BASE_URL);
        service.loadIndexArticle(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IndexArticle>() {
                    @Override
                    public void accept(IndexArticle indexArticle) throws Exception {
                            mPresenter.loadIndexArticleSucc(indexArticle);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mPresenter.loadIndexArticleFail(throwable);
                    }
                });
    }


}
