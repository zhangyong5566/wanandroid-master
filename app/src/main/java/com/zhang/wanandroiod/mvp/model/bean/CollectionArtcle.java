package com.zhang.wanandroiod.mvp.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CollectionArtcle {

    /**
     * data : {"curPage":1,"datas":[{"author":"xiaweizi","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"一个小型新闻客户端，我的毕设项目","envelopePic":"http://www.wanandroid.com/blogimgs/1c09f839-bf49-4a4f-a6e6-1c27fe41160a.png","id":4134,"link":"http://www.wanandroid.com/blog/show/2068","niceDate":"1小时前","origin":"","originId":2451,"publishTime":1520317552000,"title":"新闻客户端-QNews","userId":3536,"visible":0,"zan":0},{"author":"CarGuo","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"更好的体验，更丰富的功能，旨在更好的日常管理和维护个人Github，提供更好更方便的驾车体验～～Σ(￣。￣ﾉ)ﾉ。","envelopePic":"http://www.wanandroid.com/blogimgs/51e36466-a64d-488e-a9ff-5d17dadcfa03.png","id":4113,"link":"http://www.wanandroid.com/blog/show/2061","niceDate":"3小时前","origin":"","originId":2431,"publishTime":1520308039000,"title":"开源跨平台的开源Github客户端App GSYGithubAPP","userId":3536,"visible":0,"zan":0},{"author":"dingjikerbo","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":4112,"link":"http://www.wanandroid.com/blog/show/2067","niceDate":"3小时前","origin":"","originId":2446,"publishTime":1520308005000,"title":"Leetcode刷题之旅","userId":3536,"visible":0,"zan":0},{"author":"AWeiLoveAndroid","chapterId":73,"chapterName":"面试相关","courseId":13,"desc":"","envelopePic":"","id":4111,"link":"https://www.jianshu.com/p/c70989bd5f29","niceDate":"3小时前","origin":"","originId":2449,"publishTime":1520308003000,"title":"最全的BAT大厂面试题整理","userId":3536,"visible":0,"zan":0},{"author":"ryderchan","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":4110,"link":"http://www.wanandroid.com/blog/show/2066","niceDate":"3小时前","origin":"","originId":2447,"publishTime":1520308002000,"title":"剑指offer（第二版）java实现导航帖","userId":3536,"visible":0,"zan":0},{"author":"kyleduo","chapterId":176,"chapterName":"个人博客","courseId":13,"desc":"","envelopePic":"","id":4106,"link":"https://blog.kyleduo.com/","niceDate":"4小时前","origin":"","originId":1367,"publishTime":1520307361000,"title":"kyleduo的技术博客","userId":3536,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":6}
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

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"author":"xiaweizi","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"一个小型新闻客户端，我的毕设项目","envelopePic":"http://www.wanandroid.com/blogimgs/1c09f839-bf49-4a4f-a6e6-1c27fe41160a.png","id":4134,"link":"http://www.wanandroid.com/blog/show/2068","niceDate":"1小时前","origin":"","originId":2451,"publishTime":1520317552000,"title":"新闻客户端-QNews","userId":3536,"visible":0,"zan":0},{"author":"CarGuo","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"更好的体验，更丰富的功能，旨在更好的日常管理和维护个人Github，提供更好更方便的驾车体验～～Σ(￣。￣ﾉ)ﾉ。","envelopePic":"http://www.wanandroid.com/blogimgs/51e36466-a64d-488e-a9ff-5d17dadcfa03.png","id":4113,"link":"http://www.wanandroid.com/blog/show/2061","niceDate":"3小时前","origin":"","originId":2431,"publishTime":1520308039000,"title":"开源跨平台的开源Github客户端App GSYGithubAPP","userId":3536,"visible":0,"zan":0},{"author":"dingjikerbo","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":4112,"link":"http://www.wanandroid.com/blog/show/2067","niceDate":"3小时前","origin":"","originId":2446,"publishTime":1520308005000,"title":"Leetcode刷题之旅","userId":3536,"visible":0,"zan":0},{"author":"AWeiLoveAndroid","chapterId":73,"chapterName":"面试相关","courseId":13,"desc":"","envelopePic":"","id":4111,"link":"https://www.jianshu.com/p/c70989bd5f29","niceDate":"3小时前","origin":"","originId":2449,"publishTime":1520308003000,"title":"最全的BAT大厂面试题整理","userId":3536,"visible":0,"zan":0},{"author":"ryderchan","chapterId":321,"chapterName":"算法","courseId":13,"desc":"","envelopePic":"","id":4110,"link":"http://www.wanandroid.com/blog/show/2066","niceDate":"3小时前","origin":"","originId":2447,"publishTime":1520308002000,"title":"剑指offer（第二版）java实现导航帖","userId":3536,"visible":0,"zan":0},{"author":"kyleduo","chapterId":176,"chapterName":"个人博客","courseId":13,"desc":"","envelopePic":"","id":4106,"link":"https://blog.kyleduo.com/","niceDate":"4小时前","origin":"","originId":1367,"publishTime":1520307361000,"title":"kyleduo的技术博客","userId":3536,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 6
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * author : xiaweizi
             * chapterId : 294
             * chapterName : 完整项目
             * courseId : 13
             * desc : 一个小型新闻客户端，我的毕设项目
             * envelopePic : http://www.wanandroid.com/blogimgs/1c09f839-bf49-4a4f-a6e6-1c27fe41160a.png
             * id : 4134
             * link : http://www.wanandroid.com/blog/show/2068
             * niceDate : 1小时前
             * origin :
             * originId : 2451
             * publishTime : 1520317552000
             * title : 新闻客户端-QNews
             * userId : 3536
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }
        }
    }
}
