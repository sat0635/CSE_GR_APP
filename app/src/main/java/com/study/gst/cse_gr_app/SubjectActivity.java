package com.study.gst.cse_gr_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.study.gst.cse_gr_app.Adapter.SubjectAdapter;
import com.study.gst.cse_gr_app.model.Subject;
import com.study.gst.cse_gr_app.model.Subject;
import com.study.gst.cse_gr_app.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectActivity extends AppCompatActivity {

    private SubjectAdapter adapter = new SubjectAdapter();
    private Retrofit retrofit;
    private ArrayList<Subject> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        new JSONTask().execute();
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Subject>> call = service.getMySubject(User.userName);

            call.enqueue(new Callback<List<Subject>>() {

                @Override
                public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                    List<Subject> subjects = response.body();
                    for (Subject subject : subjects) {
                        items.add(subject);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SubjectActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);
                    Log.d("TAG","lopal ID: "+ User.userName);
                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Subject>> call, Throwable t) {
                    Log.d("tag", "lopal fail");
                }
            });
            return "done";
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("result", "lopal onpostExecute");



        }

    }



    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
