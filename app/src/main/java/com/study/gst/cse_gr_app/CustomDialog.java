package com.study.gst.cse_gr_app;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class CustomDialog {

    private NongrAdapter adapter = new NongrAdapter();
    private Retrofit retrofit;
    private ArrayList<Gr> items = new ArrayList<>();
    private Context context;

    private String category;
    private String content;

    public CustomDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final String main_label) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        //dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);



        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText message = (EditText) dlg.findViewById(R.id.mesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);
        final TextView textView = (TextView) dlg.findViewById(R.id.title);
        switch(main_label){
            case "영어":
                textView.setText("영어 점수 변경 (ex. 오픽,IM2 or 토익스피킹,120)");
                break;
            case "글로벌역량":
                textView.setText("글로벌역량 이수여부 변경 (ex. Y or N)");
                break;
            case "창업교과목":
                textView.setText("창업교과목 이수학점 변경 (ex. 3)");
                break;
            case "SW진로설계":
                textView.setText("SW진로설계 이수학점 변경 (ex. 4)");
                break;
            case "인턴":
                textView.setText("인턴 이수학점 변경 (ex. 5)");
                break;
        }
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
                //main_label.setText(message.getText().toString());
                category=main_label;
                content=message.getText().toString();
                Toast.makeText(context, "\"" +  message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();
                new JSONTask().execute();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                new JSONTask().execute();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<Result> call = service.updateUserNonSubject(User.userName,category,content);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();

                    Log.d("TAG","lopalNongr fodiaolog1");

                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.d("TAG","lopalNongr fodiaolog2");
                }
            });
            return "done";
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("result", "lopal onpostExecute3");

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