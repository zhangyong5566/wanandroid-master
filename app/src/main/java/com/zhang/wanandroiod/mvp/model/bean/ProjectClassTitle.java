package com.zhang.wanandroiod.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassTitle {


    /**
     * data : [{"children":[],"courseId":13,"id":294,"name":"完整项目","order":145000,"parentChapterId":293,"visible":0},{"children":[],"courseId":13,"id":309,"name":"表格控件","order":145001,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":310,"name":"下拉刷新","order":145002,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":312,"name":"富文本编辑器","order":145003,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":314,"name":"RV列表动效","order":145004,"parentChapterId":293,"visible":1},{"children":[],"courseId":13,"id":316,"name":"系统源码分析","order":145005,"parentChapterId":293,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable{
        /**
         * children : []
         * courseId : 13
         * id : 294
         * name : 完整项目
         * order : 145000
         * parentChapterId : 293
         * visible : 0
         */

        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private int visible;
        private List<?> children;

        protected DataBean(Parcel in) {
            courseId = in.readInt();
            id = in.readInt();
            name = in.readString();
            order = in.readInt();
            parentChapterId = in.readInt();
            visible = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(courseId);
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeInt(order);
            dest.writeInt(parentChapterId);
            dest.writeInt(visible);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
