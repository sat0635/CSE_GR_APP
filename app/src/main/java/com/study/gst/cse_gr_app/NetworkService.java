package com.study.gst.cse_gr_app;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.Nongr;
import com.study.gst.cse_gr_app.model.Question;
import com.study.gst.cse_gr_app.model.Result;
import com.study.gst.cse_gr_app.model.Subject;
import com.study.gst.cse_gr_app.model.User;

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

    @GET("api/usernonsubjectu/{userEmail}/{category}/{content}/")
    Call<Result> updateUserNonSubject(
            @Path("userEmail") String userEmail,
            @Path("category") String category,
            @Path("content") String content
    );
    @GET("api/subject/{userEmail}/")
    Call<List<Gr>> getSubject(
            @Path("userEmail") String userEmail
    );
    @GET("api/mysubject/{userEmail}/")
    Call<List<Subject>> getMySubject(
            @Path("userEmail") String userEmail
    );
    @GET("api/nonsubject/{userEmail}/")
    Call<List<Nongr>> getNonSubject(
            @Path("userEmail") String userEmail
    );

    @GET("api/useremail/{userEmail}/")
    Call<Result> sendUserEmail(
            @Path("userEmail") String userEmail
    );

    @GET("api/question/{userEmail}/{title}/{desc}/")
    Call<Result> sendQuestion(
            @Path("userEmail") String userEmail,
            @Path("title") String title,
            @Path("desc") String desc
    );

    @GET("api/userinfo/{userEmail}/")
    Call<List<User>> getUserInfo(
            @Path("userEmail") String userEmail
    );

    @GET("api/userinfou/{userEmail}/{major}/{track}/")
    Call<List<User>>updateUserInfo(
            @Path("userEmail") String userEmail,
            @Path("major") String major,
            @Path("track") String track
    );

    @GET("api/qa/{userEmail}/")
    Call<List<Question>>getMyQuestion(
            @Path("userEmail") String userEmail
    );
    @GET("api/faq/")
    Call<List<Question>>getFaq(
    );
}
