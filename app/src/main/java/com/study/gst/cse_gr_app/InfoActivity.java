package com.study.gst.cse_gr_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.User;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InfoActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    private ImageView excel;
    TextView textViewMajor;
    TextView textViewTrack;
    private String S1="";
    private String S2="";
    Button update1;
    Button update2;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        textViewMajor = (TextView)findViewById(R.id.infoActivity_major);
        textViewTrack= (TextView)findViewById(R.id.infoActivity_track);
        new JSONTask().execute();
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



        excel = (ImageView) findViewById(R.id.infoActivity_imageview);
        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //다운로드폴더가 루트파일
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/Download/");
                //엑셀파일만
                intent.setDataAndType(uri, "*/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });


        update1 = (Button) findViewById(R.id.infoActivity_update1);
        update1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final List<String> selectedItems = new ArrayList<String>();
                final String[] items = new String[]{"심화컴퓨터","글로벌SW융합","SW연계융합"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(InfoActivity.this);
                dialog  .setTitle("관심분야를 선택하세요")
                        .setMultiChoiceItems(items,
                                new boolean[]{false, false, false, false, false, false, false, false},
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if(isChecked) {
                                            Toast.makeText(InfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                            selectedItems.add(items[which]);
                                        } else {
                                            selectedItems.remove(items[which]);
                                        }
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if( selectedItems.size() == 0) {
                                    Toast.makeText(InfoActivity.this, "선택된 관심분야가 없습니다", Toast.LENGTH_SHORT).show();
                                } else {
                                    String items = "";
                                    for (String selectedItem : selectedItems) {
                                        items += (selectedItem + ", ");
                                    }

                                    selectedItems.clear();

                                    items = items.substring(0, items.length() - 2);
                                    Toast.makeText(InfoActivity.this, items, Toast.LENGTH_SHORT).show();
                                    S1=items;
                                    S2="N";
                                    new JSONTaskInfo().execute();
                                }

                            }
                        }).create().show();
            }
        });
        update2 = (Button) findViewById(R.id.infoActivity_update2);
        update2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final List<String> selectedItems = new ArrayList<String>();
                Log.d("TAG","lopal update2 onclick"+textViewMajor.getText().toString());
                String[] items = null;
                if( textViewMajor.getText().toString().compareTo ("심화컴퓨터")==0){
                    items = new String[]{"ABEEK","아무것도아님"};
                }
                else if( textViewMajor.getText().toString().compareTo("글로벌SW융합")==0){
                    items = new String[]{"다중전공","해외복수학위","학석사연계"};
                }
                else if (textViewMajor.getText().toString().compareTo("SW연계융합")==0){
                    items = new String[]{"연계전공","융합전공","복수전공","부전공"};
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(InfoActivity.this);
                final String[] finalItems = items;
                dialog  .setTitle("관심분야를 선택하세요")
                        .setMultiChoiceItems(finalItems,
                                new boolean[]{false, false, false, false, false, false, false, false},
                                new DialogInterface.OnMultiChoiceClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if(isChecked) {
                                            Toast.makeText(InfoActivity.this, finalItems[which], Toast.LENGTH_SHORT).show();
                                            selectedItems.add(finalItems[which]);
                                        } else {
                                            selectedItems.remove(finalItems[which]);
                                        }
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if( selectedItems.size() == 0) {
                                    Toast.makeText(InfoActivity.this, "선택된 관심분야가 없습니다", Toast.LENGTH_SHORT).show();
                                } else {
                                    String items = "";
                                    for (String selectedItem : selectedItems) {
                                        items += (selectedItem + ", ");
                                    }

                                    selectedItems.clear();

                                    items = items.substring(0, items.length() - 2);
                                    Toast.makeText(InfoActivity.this, items, Toast.LENGTH_SHORT).show();
                                    S1=textViewMajor.getText().toString();
                                    S2=items;
                                    new JSONTaskInfo().execute();
                                }

                            }
                        }).create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE) {
        }
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<User>> call = service.getUserInfo(User.userName);

            call.enqueue(new Callback<List<User>>() {

                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> users = response.body();
                    for (User user : users) {
                        Log.d("TAG", "lopal update1" + user.getMajor());
                        textViewMajor.setText(user.getMajor());
                        textViewTrack.setText(user.getTrack());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
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
    public class JSONTaskInfo extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<User>> call = service.updateUserInfo(User.userName,S1,S2);

            call.enqueue(new Callback<List<User>>() {

                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> users = response.body();
                    for (User user : users) {
                        Log.d("TAG", "loapl update2" + user.getMajor());
                        textViewMajor.setText(user.getMajor());
                        textViewTrack.setText(user.getTrack());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
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
