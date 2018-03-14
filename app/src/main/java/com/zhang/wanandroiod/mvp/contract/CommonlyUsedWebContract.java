package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;

/**
 * Created by Administrator on 2018/3/1.
 * 常用网站
 */

public interface CommonlyUsedWebContract {
    interface Model {
        void loadCommonlyUsedWeb();
    }

    interface View {
        void loadCommonlyUsedWebSucc(CommonlyUsedWeb commonlyUsedWeb);
        void loadCommonlyUsedWebFail(Throwable ex);
    }

    interface Presenter {
        void loadCommonlyUsedWeb();
        void loadCommonlyUsedWebSucc(CommonlyUsedWeb commonlyUsedWeb);
        void loadCommonlyUsedWebFail(Throwable ex);
    }
}
