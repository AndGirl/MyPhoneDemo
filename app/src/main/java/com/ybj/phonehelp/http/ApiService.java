package com.ybj.phonehelp.http;

import com.ybj.phonehelp.bean.AppInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 杨阳洋 on 2017/12/25.
 * 网络地址接口
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    Observable<AppInfo> getApps(@Query("p") String jsonParam);

}
