package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.Navigation;

/**
 * Created by Administrator on 2018/3/2.
 */

public interface NavigationContract {
    interface Model {
        void loadNavigation();

    }

    interface View {
        void loadNavigationSucc(Navigation navigation);
        void loadNavigationFail(Throwable ex);
    }

    interface Presenter {
        void loadNavigation();
        void loadNavigationSucc(Navigation navigation);
        void loadNavigationFail(Throwable ex);
    }
}
