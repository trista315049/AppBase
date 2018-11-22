package com.oit.appbase.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author trista
 * @date 2018/4/25
 * @function
 */
public interface ApiService {
    /**
     * 上传文件
     * "fileImage\"; filename=\"messenger_01.jpg"
     */
    @Multipart
    @POST("{url}")
    Observable<String> upFiles(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps,
            @PartMap Map<String, RequestBody> bodyMap);

    @GET("{url}")
    Observable<String> get(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps);

    @FormUrlEncoded
    @POST("{url}")
    Observable<String> post(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, String> maps);


    @DELETE("{url}")
    Observable<String> delete(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps);

}
