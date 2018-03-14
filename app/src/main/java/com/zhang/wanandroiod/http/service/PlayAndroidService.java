package com.zhang.wanandroiod.http.service;

import com.zhang.wanandroiod.mvp.model.bean.AddCollection;
import com.zhang.wanandroiod.mvp.model.bean.ArtcleSystemTitle;
import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;
import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;
import com.zhang.wanandroiod.mvp.model.bean.HotSeachKey;
import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;
import com.zhang.wanandroiod.mvp.model.bean.IndexBanner;
import com.zhang.wanandroiod.mvp.model.bean.Navigation;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClassTitle;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;
import com.zhang.wanandroiod.mvp.model.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/2/27.
 */

public interface PlayAndroidService {
    /**
     * 1.首页相关
     * 1.1 首页文章列表
     * http://www.wanandroid.com/article/list/0/json
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{index}/json")
    Observable<IndexArticle> loadIndexArticle(@Path("index") int index);

    /**
     * 1.2 首页banner
     * http://www.wanandroid.com/banner/json
     * 方法：GET
     * 参数：无
     */
    @GET("banner/json")
    Observable<IndexBanner> loadIndexBanner();

    /**
     * 1.3 常用网站
     * http://www.wanandroid.com/friend/json
     * 方法：GET
     * 参数：无
     */
    @GET("friend/json")
    Observable<CommonlyUsedWeb> loadCommonlyUsedWeb();


    /**
     * 1.4 搜索热词
     * <p>
     * 即目前搜索最多的关键词。
     * <p>
     * http://www.wanandroid.com//hotkey/json
     * <p>
     * 方法：GET
     * 参数：无
     */
    @GET("hotkey/json")
    Observable<HotSeachKey> loadHotSeachKey();

    /**
     * 2. 体系
     * 2.1 体系数据
     * http://www.wanandroid.com/tree/json
     * 方法：GET
     * 参数：无
     */

    @GET("tree/json")
    Observable<ArtcleSystemTitle> loadArtcleSystemTitle();

    /**
     * 2.2 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * 方法：GET
     * 参数：
     * cid 分类的id，上述二级目录的id
     * 页码：拼接在链接上，从0开始。
     */
    @GET("article/list/{index}/json")
    Observable<SystemArtcle> loadSystemArtcle(@Path("index") int index, @Query("cid") int cid);

    /**
     * 3. 导航
     * 3.1 导航数据
     * http://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    @GET("navi/json")
    Observable<Navigation> loadNavigation();

    /**
     * 4. 项目
     * 4.1 项目分类
     * http://www.wanandroid.com/project/tree/json
     * 方法： GET
     * 参数： 无
     */
    @GET("project/tree/json")
    Observable<ProjectClassTitle> loadProjectClassTitle();

    /**
     * 4.2 项目列表数据
     * 某一个分类下项目列表数据，分页展示
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * 方法：GET
     * 参数：
     * cid 分类的id，上面项目分类接口
     * 页码：拼接在链接中，从1开始。
     */
    @GET("project/list/{index}/json")
    Observable<ProjectClass> loadProjectClass(@Path("index") int index, @Query("cid") int cid);


    /**
     * 5.1 注册
     * <p>
     * http://www.wanandroid.com/user/register
     * <p>
     * 方法：POST
     * 参数
     * username,password,repassword
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<UserInfo> userRegist(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 5.2 登录
     * <p>
     * http://www.wanandroid.com/user/login
     * <p>
     * 方法：POST
     * 参数：
     * username，password
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserInfo> login(@Field("username") String username, @Field("password") String pwd);

    /**
     * 6.1 收藏文章列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     */
    @GET("lg/collect/list/{index}/json")
    Observable<CollectionArtcle> loadCollectionArtcle(@Path("index") int index);

    /**
     * 6.2 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * 方法：POST
     * 参数： 文章id，拼接在链接中。
     */

    @FormUrlEncoded
    @POST("lg/collect/{index}/json")
    Observable<AddCollection> addCollection(@Path("index") int index, @Field("id") int id);

    /**
     * 6.4.1 取消收藏
     * <p>
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * id传入的是列表中文章的id
     */
    @FormUrlEncoded
    @POST("lg/uncollect_originId/{index}/json")
    Observable<AddCollection> removeCollection(@Path("index") int index, @Field("id") int id);

    /**
     * 7. 搜索

     7.1 搜索

     http://www.wanandroid.com/article/query/0/json

     方法：POST
     参数：
     页码：拼接在链接上，从0开始。
     k ： 搜索关键词
     */
    @FormUrlEncoded
    @POST("/article/query/{index}/json")
    Observable<SystemArtcle >loadSeach(@Path("index") int index, @Field("k") String key);
}
