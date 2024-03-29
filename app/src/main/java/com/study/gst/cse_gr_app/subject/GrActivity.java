package com.study.gst.cse_gr_app.subject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.study.gst.cse_gr_app.Adapter.GrAdapter;
import com.study.gst.cse_gr_app.setting.Config;
import com.study.gst.cse_gr_app.setting.NetworkService;
import com.study.gst.cse_gr_app.R;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GrActivity extends AppCompatActivity{

    private GrAdapter adapter = new GrAdapter();
    private Retrofit retrofit;
    private ArrayList<Gr> items = new ArrayList<>();
    private Button btSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gr);

        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //왼쪽의 메뉴탭 반응
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //좌측 상단 탭 메뉴
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                 int id = item.getItemId();
                                                                 if (id == R.id.nav_home) {
                                                                     DrawerLayout drawer = findViewById(R.id.drawer_layout);
                                                                     drawer.closeDrawer(GravityCompat.START);
                                                                     return true;
                                                                     //인증샷 갤러리
                                                                 } else if (id == R.id.nav_missionPlace) {


                                                                     return true;

                                                                 } else if (id == R.id.nav_ranking) {


                                                                     return true;

                                                                 }


                                                                 return true;
                                                             }
                                                         }

        );
        btSubjects=(Button) findViewById(R.id.subjects);
        btSubjects.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GrActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });
        new JSONTask().execute();
    }



    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Gr>> call = service.getSubject(User.userName);

            call.enqueue(new Callback<List<Gr>>() {

                @Override
                public void onResponse(Call<List<Gr>> call, Response<List<Gr>> response) {
                    List<Gr> grs = response.body();
                    for (Gr gr : grs) {
                        Log.d("TAG","lopal2"+gr.getCategory());
                        items.add(gr);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(GrActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);
                    Log.d("TAG","lopal ID: "+ User.userName);
                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Gr>> call, Throwable t) {
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
