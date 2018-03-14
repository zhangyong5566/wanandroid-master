package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

/**
 * Created by Administrator on 2018/2/27.
 */

public interface LoginContract {
    interface Model {
        void login(String username,String pwd);

    }

    interface View {
        void loginSucc(UserInfo userInfo);
        void logingFail(Throwable ex);
    }

    interface Presenter {
        void login(String username,String pwd);
        void loginSucc(UserInfo userInfo);
        void logingFail(Throwable ex);
    }
}
