package com.study.gst.cse_gr_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.gst.cse_gr_app.Adapter.NongrAdapter;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.Result;
import com.study.gst.cse_gr_app.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QActivity extends AppCompatActivity {
    private Button submit_button;
    private EditText title;
    private EditText desc;
    private NongrAdapter adapter = new NongrAdapter();
    private Retrofit retrofit;
    private ArrayList<Gr> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);

        submit_button = (Button) findViewById(R.id.submit_button);
        title = (EditText)findViewById(R.id.title_edittext);
        desc = (EditText)findViewById(R.id.desc_edittext);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONTask().execute();
            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            Log.d("TAG","lopal"+title.getText().toString() +":"+desc.getText().toString());
            NetworkService service = retrofit.create(NetworkService.class);
            Call<Result> call = service.sendQuestion(User.userName, title.getText().toString() ,desc.getText().toString());

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result resultValue = response.body();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
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
            finish();
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
