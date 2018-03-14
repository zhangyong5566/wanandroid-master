package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;

/**
 * Created by Administrator on 2018/3/3.
 */

public interface ProjectClassTitleContract {
    interface Model {
        void loadProjectClassTitle();
    }

    interface View {
        void loadProjectClassTitleSucc(ProjectClassTitle mProjectClassTitle);
        void loadProjectClassTitleFail(Throwable ex);
    }

    interface Presenter {
        void loadProjectClassTitle();
        void loadProjectClassTitleSucc(ProjectClassTitle mProjectClassTitle);
        void loadProjectClassTitleFail(Throwable ex);
    }
}
