package com.zhang.wanandroiod.mvp.contract;

/**
 * Created by Administrator on 2018/3/1.
 * 热门搜索
 */

public interface PopularSitesContract {
    interface Model {
        void loadPopularSites();
    }

    interface View {
        void loadPopularSitesSucc();
        void loadPopularSitesFail();
    }

    interface Presenter {
        void loadPopularSites();
        void loadPopularSitesSucc();
        void loadPopularSitesFail();
    }
}
