package com.zhang.wanandroiod.mvp.presenter;

import com.zhang.wanandroiod.mvp.contract.ArticleContract;
import com.zhang.wanandroiod.mvp.model.ArticleModel;
import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;

/**
 * Created by Administrator on 2018/2/27.
 */

public class ArticlePresenter implements ArticleContract.Presenter {
    private ArticleContract.View mView;
    private ArticleContract.Model mModel;

    public ArticlePresenter(ArticleContract.View view) {
        mView = view;
        mModel = new ArticleModel(this);
    }

    @Override
    public void loadIndexArticle(int index) {
        mModel.loadIndexArticle(index);
    }

    @Override
    public void loadIndexArticleSucc(IndexArticle indexArticle) {
        mView.loadIndexArticleSucc(indexArticle);
    }

    @Override
    public void loadIndexArticleFail(Throwable ex) {
        mView.loadIndexArticleFail(ex);
    }
}
