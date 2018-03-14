package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;

/**
 * Created by Administrator on 2018/2/27.
 */

public interface ArticleContract {
    interface Model {
         void loadIndexArticle(int page);

    }

    interface View {
        void loadIndexArticleSucc(IndexArticle indexArticle);
        void loadIndexArticleFail(Throwable ex);
    }

    interface Presenter {
        void loadIndexArticle(int page);
        void loadIndexArticleSucc(IndexArticle indexArticle);
        void loadIndexArticleFail(Throwable ex);
    }
}
