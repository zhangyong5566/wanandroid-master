package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface SeachContract {
    interface Model {
        void loadSeach(int index,String key);

    }

    interface View {
        void loadSeachSucc(SystemArtcle systemArtcle);
        void loadSeachFail(Throwable ex);
    }

    interface Presenter {
        void loadSeach(int index,String key);
        void loadSeachSucc(SystemArtcle systemArtcle);
        void loadSeachFail(Throwable ex);
    }
}
