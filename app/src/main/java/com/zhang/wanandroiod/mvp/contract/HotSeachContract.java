package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.HotSeachKey;

/**
 * Created by Administrator on 2018/3/14.
 */

public interface HotSeachContract {
    interface Model {
       void loadHotSeachKey();

    }

    interface View {
        void loadHotSeachKeySucc(HotSeachKey hotSeachKey);
        void loadHotSeachKeyFail(Throwable ex);
    }

    interface Presenter {
        void loadHotSeachKey();
        void loadHotSeachKeySucc(HotSeachKey hotSeachKey);
        void loadHotSeachKeyFail(Throwable ex);
    }
}
