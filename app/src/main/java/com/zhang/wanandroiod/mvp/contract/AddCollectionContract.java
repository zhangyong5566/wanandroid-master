package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.AddCollection;

/**
 * Created by Administrator on 2018/3/7.
 */

public interface AddCollectionContract {
    interface Model {
        void addCollection(int id);
    }

    interface View {
        void addCollectionSucc(AddCollection addCollection);
        void addCollectionFail(Throwable ex);
    }

    interface Presenter {
        void addCollection(int id);
        void addCollectionSucc(AddCollection addCollection);
        void addCollectionFail(Throwable ex);
    }
}
