package com.ybj.phonehelp.http;

import android.content.Context;

import com.google.gson.Gson;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.DensityUtil;
import com.ybj.phonehelp.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 公共参数的接口
 * http://www.tk4479.net/wuyinlei/article/details/57087872
 */

public class CommonParamsInterceptor implements Interceptor {
    public static final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context,Gson gson){

        this.mContext = context;
        this.mGson = gson;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        try{
            HashMap<String,Object> commomParamsMap = new HashMap<>();

            commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");
            if("GET".equals(method)) {
                HashMap<String,Object> rootMap = new HashMap<>();
                HttpUrl url = request.url();
                Set<String> parameterNames = url.queryParameterNames();
                for (String key : parameterNames){
                    if(Constant.PARAM.equals(key)) {//需要考虑不传参的情况
                        String oldParams = url.queryParameter(Constant.PARAM);
                        if(oldParams != null) {
                            HashMap<String,Object> p = mGson.fromJson(oldParams, HashMap.class);
                            if(p != null) {
                                for (Map.Entry<String,Object> entry : p.entrySet()){
                                    rootMap.put(entry.getKey(),entry.getValue());
                                }
                            }
                        }
                    }else{
                        rootMap.put(key,url.queryParameter(key));
                    }
                }

                //加公共参数
                rootMap.put("publicParams",commomParamsMap);
                //新的json参数
                String newJson = mGson.toJson(rootMap);
                String newUrl = url.toString();
                int index = newUrl.indexOf("?");
                if(index > 0) {
                    newUrl = newUrl.substring(0,index);
                }
                newUrl = newUrl + "?" + Constant.PARAM + "=" + newJson;
                request = request.newBuilder().url(newUrl).build();

            }else if("POST".equals(method)) {
                RequestBody body = request.body();
                HashMap<String, Object> rootMap = new HashMap<>();
                if(body instanceof FormBody) {
                    for (int i = 0 ;i < ((FormBody) body).size() ; i ++){
                        rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));
                    }
                }else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParam = buffer.readUtf8();
                    rootMap = mGson.fromJson(oldJsonParam,HashMap.class);
                    rootMap.put("publicParams",commomParamsMap); // 重新组装
                    String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                }
            }

        }catch (Exception e){
            e.getMessage();
        }
        return chain.proceed(request);
    }

}
