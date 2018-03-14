package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

/**
 * Created by Administrator on 2018/3/5.
 */

public interface RegistContract {
    interface Model {
        void userRegist(String username,String pwd);

    }

    interface View {
        void userRegistSucc(UserInfo info);
        void userRegistFail(Throwable ex);
    }

    interface Presenter {
        void userRegist(String username,String pwd);
        void userRegistSucc(UserInfo info);
        void userRegistFail(Throwable ex);
    }
}
