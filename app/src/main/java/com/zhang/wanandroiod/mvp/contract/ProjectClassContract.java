package com.zhang.wanandroiod.mvp.contract;

import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;

/**
 * Created by Administrator on 2018/3/3.
 */

public interface ProjectClassContract {
    interface Model {
        void loadProjectClass(int index,int cid);

    }

    interface View {
        void loadProjectClassSucc(ProjectClass projectClass);

        void loadProjectClassFail(Throwable ex);
    }

    interface Presenter {
        void loadProjectClass(int index,int cid);

        void loadProjectClassSucc(ProjectClass projectClass);

        void loadProjectClassFail(Throwable ex);
    }
}
