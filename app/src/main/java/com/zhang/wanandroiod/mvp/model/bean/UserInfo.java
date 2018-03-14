package com.zhang.wanandroiod.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class UserInfo {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":3527,"password":"123556","type":0,"username":"123556"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean implements Parcelable{
        /**
         * collectIds : []
         * email :
         * icon :
         * id : 3527
         * password : 123556
         * type : 0
         * username : 123556
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private int type;
        private String username;
        private List<?> collectIds;

        protected DataBean(Parcel in) {
            email = in.readString();
            icon = in.readString();
            id = in.readInt();
            password = in.readString();
            type = in.readInt();
            username = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(email);
            dest.writeString(icon);
            dest.writeInt(id);
            dest.writeString(password);
            dest.writeInt(type);
            dest.writeString(username);
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<?> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
