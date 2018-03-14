package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;

/**
 * Created by Administrator on 2018/3/6.
 */

public interface CollectionArtcleContract {
    interface Model {
        void loadCollectionArtcle(int index);
    }

    interface View {
        void loadCollectionArtcleSucc(CollectionArtcle collectionArtcle);
        void loadCollectionArtcleFail(Throwable ex);
    }

    interface Presenter {
        void loadCollectionArtcle(int index);
        void loadCollectionArtcleSucc(CollectionArtcle collectionArtcle);
        void loadCollectionArtcleFail(Throwable ex);
    }
}
