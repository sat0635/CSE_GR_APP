package com.study.gst.cse_gr_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;

import android.view.Menu;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Retrofit;


//홈 메인페이지
//GPS 설정 여부 체크
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Retrofit retrofit;
    private Button mission_button;
    private Button place_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                                                 }  else if (id == R.id.nav_missionPlace) {


                                                                     return true;

                                                                 } else if (id == R.id.nav_ranking) {


                                                                     return true;

                                                                 }


                                                                 return true;
                                                             }
                                                         }

        );


        mission_button = (Button) findViewById(R.id.mission_button);
        place_button = (Button) findViewById(R.id.place_button);
        mission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GrActivity.class);
                startActivity(intent);

            }
        });

        //이벤트 불러오기


        //하단의 메뉴판 가져오기
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainactivity_bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_store:

                        return true;
                    case R.id.action_account:
                        Intent intent_act = new Intent(getApplicationContext(), ExcelActivity.class);
                        startActivity(intent_act);
                        return true;
                }
                return false;
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }  else if (id == R.id.nav_missionPlace) {

            //  Intent intent_act = new Intent(getApplicationContext(), MissionPlace.class);
            //startActivity(intent_act);
            return true;

        } else if (id == R.id.nav_ranking) {

            //intent intent_act = new Intent(getApplicationContext(), Ranking.class);
            //startActivity(intent_act);
            return true;

        }

        return true;
    }
}