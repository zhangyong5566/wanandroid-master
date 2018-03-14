package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.AddCollection;

/**
 * Created by Administrator on 2018/3/7.
 */

public interface RemoveCollectionContract {
    interface Model {
       void removeCollection(int index);

    }

    interface View {
        void removeCollectionSucc(AddCollection collection);
        void removeCollectionFail(Throwable ex);
    }

    interface Presenter {
        void removeCollection(int index);
        void removeCollectionSucc(AddCollection collection);
        void removeCollectionFail(Throwable ex);
    }
}
