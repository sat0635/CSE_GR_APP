package com.study.gst.cse_gr_app.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.gst.cse_gr_app.NonSubjectDialog;
import com.study.gst.cse_gr_app.R;
import com.study.gst.cse_gr_app.model.Nongr;

import java.util.ArrayList;

public class NongrAdapter extends RecyclerView.Adapter<NongrAdapter.ViewHolder>  {
    private ArrayList<Nongr> items = new ArrayList<>();

    @NonNull
    @Override
    public NongrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nongr_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NongrAdapter.ViewHolder viewHolder, int position) {
        Nongr item = items.get(position);
        String category = item.getCategory();
        String content = item.getContent();

        switch(category){
            case "영어":
                break;
            case "글로벌역량":
                if (content.split(",")[0].compareTo("N")==0){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
                }else if(content.split(",")[0].compareTo("Y")==0){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
                }
                break;
            case "창업교과목":
                if ( (content.split(",")[0]) instanceof String && content.split(",")[0].compareTo("N")==0){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
                }
                else if(Integer.parseInt(content.split(",")[0])  >= Integer.parseInt(content.split(",")[0])){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
                }
                break;
            case "SW진로설계":
                if ( (content.split(",")[0]) instanceof String && content.split(",")[0].compareTo("N")==0){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
                }
                else if(Integer.parseInt(content.split(",")[0])  >= Integer.parseInt(content.split(",")[0])){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
                }
                break;
            case "인턴":
                if ( (content.split(",")[0]) instanceof String && content.split(",")[0].compareTo("N")==0){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
                }
                else if(Integer.parseInt(content.split(",")[0])  >= Integer.parseInt(content.split(",")[0])){
                    viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
                }
                break;
        }
        Log.d("TAG","lopalNongr "+content);
        viewHolder.btContent.setText(category);
        viewHolder.tvTest1.setText("나의 상태");
        viewHolder.tvTest2.setText(content.split(",")[0]);
        viewHolder.tvTest3.setText("졸업요건");
        viewHolder.tvTest4.setText(content.split(",")[1]);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Nongr> items)
    {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button btContent;
        TextView tvTest1,tvTest2,tvTest3,tvTest4;
        ViewHolder(View itemView) {
            super(itemView);
            btContent = itemView.findViewById((R.id.subject_class));
            btContent.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    NonSubjectDialog nonSubjectDialog = new NonSubjectDialog(view.getContext());
                    // 커스텀 다이얼로그를 호출한다.
                    // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                    nonSubjectDialog.callFunction(btContent.getText().toString());



             }});
            tvTest1 = itemView.findViewById(R.id.test1);
            tvTest2 = itemView.findViewById(R.id.test2);
            tvTest3 = itemView.findViewById(R.id.test3);
            tvTest4 = itemView.findViewById(R.id.test4);
        }
    }
}
