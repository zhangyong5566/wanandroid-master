package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;

/**
 * Created by Administrator on 2018/3/1.
 */

public interface ArtcleSystemTitleContract {
    interface Model {
        void loadArtcleSystemTitle();
    }

    interface View {
        void loadArtcleSystemTitleSucc(ArtcleSystemTitle artcleSystemTitle);
        void loadArtcleSystemTitleFail(Throwable ex);
    }

    interface Presenter {
        void loadArtcleSystemTitle();
        void loadArtcleSystemTitleSucc(ArtcleSystemTitle artcleSystemTitle);
        void loadArtcleSystemTitleFail(Throwable ex);
    }
}
