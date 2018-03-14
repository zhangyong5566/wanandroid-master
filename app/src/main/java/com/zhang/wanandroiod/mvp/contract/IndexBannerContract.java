package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.IndexBanner;

/**
 * Created by Administrator on 2018/2/28.
 */

public interface IndexBannerContract {
    interface Model {
        void loadBanner();
    }

    interface View {
        void loadSucc(IndexBanner indexBanner);
        void loadFail(Throwable ex);
    }

    interface Presenter {
        void loadBanner();
        void loadSucc(IndexBanner indexBanner);
        void loadFail(Throwable ex);
    }
}
