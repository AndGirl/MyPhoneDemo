package com.ybj.phonehelp.http;

import com.ybj.phonehelp.bean.AppDetailsBean;
import com.ybj.phonehelp.bean.AppInfo;
import com.ybj.phonehelp.bean.BaseBean;
import com.ybj.phonehelp.bean.CategoryBean;
import com.ybj.phonehelp.bean.GameBean;
import com.ybj.phonehelp.bean.LoginBean;
import com.ybj.phonehelp.bean.LoginRequestBean;
import com.ybj.phonehelp.bean.RankingBean;
import com.ybj.phonehelp.bean.RecommendBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 杨阳洋 on 2017/12/25.
 * 网络地址接口
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured2")
    Observable<BaseBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("index")
    Observable<BaseBean<RecommendBean>> index(@Query("p") String jsonParam);

    @POST("login")
    Observable <BaseBean<LoginBean>> login(@Body LoginRequestBean loginRequestBean);

    @FormUrlEncoded // FormBody
    @POST("login")
    public void login2(@Field("phone") String phone);

    @GET("toplist")
    Observable<BaseBean<RankingBean>> toplist(@Query("page") int page);

    @GET("game")
    Observable<BaseBean<GameBean>> game(@Query("page") int page);

    @GET("category")
    Observable<BaseBean<List<CategoryBean>>> category();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<GameBean>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<GameBean>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<GameBean>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("app/{id}")
    Observable<BaseBean<AppDetailsBean>> getAppDetailsById(@Path("id") int id);

}
