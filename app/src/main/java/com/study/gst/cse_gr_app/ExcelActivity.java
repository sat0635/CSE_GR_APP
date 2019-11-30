package com.study.gst.cse_gr_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.StorageReference;
import com.study.gst.cse_gr_app.model.Result;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ExcelActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    private ImageView excel;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
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


        excel = (ImageView) findViewById(R.id.signupActivity_imageview_profile);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE) {
            File file = new File("/storage/emulated/0/Download/전체성적.xls");
            HttpMultiPart(file);
        }


    }

    private void HttpMultiPart(final File file){

        new AsyncTask<Void, Void, JSONObject>(){

            @Override
            protected JSONObject doInBackground(Void... voids) {

                String boundary = "^-----^";
                String LINE_FEED = "\r\n";
                String charset = "UTF-8";
                OutputStream outputStream;
                PrintWriter writer;

                JSONObject result = null;
                try{

                    URL url = new URL("http://35.213.81.134:8000/api/excel/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(15000);

                    outputStream = connection.getOutputStream();
                    writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

                    /** Body에 데이터를 넣어줘야 할경우 없으면 Pass **/
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"데이터 키값\"").append(LINE_FEED);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.append("데이터값").append(LINE_FEED);
                    writer.flush();

                    /** 파일 데이터를 넣는 부분**/
                    writer.append("--" + boundary).append(LINE_FEED);
                    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(LINE_FEED);
                    writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(LINE_FEED);
                    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
                    writer.append(LINE_FEED);
                    writer.flush();

                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[(int)file.length()];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                    inputStream.close();
                    writer.append(LINE_FEED);
                    writer.flush();

                    writer.append("--" + boundary + "--").append(LINE_FEED);
                    writer.close();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        try {
                            result = new JSONObject(response.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        result = new JSONObject(response.toString());
                    }

                } catch (ConnectException e) {
                    Log.e("TAG", "ConnectException");
                    e.printStackTrace();


                } catch (Exception e){
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
            }

        }.execute();
    }




}
