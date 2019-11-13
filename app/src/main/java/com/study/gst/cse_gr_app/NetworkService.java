package com.study.gst.cse_gr_app;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface NetworkService {
    @GET("api/grsubject/{id}")
    Call<List<Subject>> get_grsubject(
            @Path("id") String id
    );
}
