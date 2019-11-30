package com.study.gst.cse_gr_app;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.Result;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NetworkService {
    @Multipart
    @POST("api/excel/")
    Call<Result> sendExcel(
            @Part MultipartBody.Part filePart
    );
    @GET("api/gr/{userId}/")
    Call<List<Gr>> getGr(
            @Path("userId") String userId
    );
}
