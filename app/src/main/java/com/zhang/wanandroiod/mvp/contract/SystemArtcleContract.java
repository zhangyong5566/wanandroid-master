package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

/**
 * Created by Administrator on 2018/3/2.
 * 体系下的文章
 */

public interface SystemArtcleContract {
    interface Model {
        void loadSystemArtcle(int page,int cid);
    }

    interface View {
        void loadSystemArtcleSucc(SystemArtcle systemArtcle);
        void loadSystemArtcleFail(Throwable ex);
    }

    interface Presenter {
        void loadSystemArtcle(int page,int cid);
        void loadSystemArtcleSucc(SystemArtcle systemArtcle);
        void loadSystemArtcleFail(Throwable ex);
    }
}
