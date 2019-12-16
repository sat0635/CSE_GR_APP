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

import com.study.gst.cse_gr_app.R;
import com.study.gst.cse_gr_app.model.Gr;
import java.util.ArrayList;

public class GrAdapter extends RecyclerView.Adapter<GrAdapter.ViewHolder>  {
    private ArrayList<Gr> items = new ArrayList<>();

    @NonNull
    @Override
    public GrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gr_subject_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GrAdapter.ViewHolder viewHolder, int position) {
        Gr item = items.get(position);
        String category = item.getCategory();
        String grade = item.getGrade().split(":")[0];
        Log.d("TAG","lopal Bind"+item.getGrade());
        String requirement = item.getGrade().split(":")[1];
        //심화컴퓨터


        if (category.compareTo("TOTAL") == 0){
            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("총 학점");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);
        }
        else if (category.compareTo("MAJOR") == 0){
            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("전공");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);
        }
        else if (category.compareTo("BASEMAJOR") == 0){
            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("전공기반");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);
        }
        else if (category.compareTo("ENGINEER_CUL") == 0){
            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("기본소양");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);
        }else if (category.compareTo("SW_MAJOR") == 0){ // 글로벌 SW 융합
            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("전공");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("51");
        }
        else if (category.compareTo("SW_CUL") == 0){
            if (Integer.parseInt(grade) < Integer.parseInt(requirement.split("~")[0])  || Integer.parseInt(grade) > Integer.parseInt(requirement.split("~")[1])  ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }
            viewHolder.btContent.setText("교양");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("24~42");
        }else if (category.compareTo("SW_CONN_MAJOR")==0){

            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }

            viewHolder.btContent.setText("총 학점");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);

        }else if (category.compareTo("SW_CONN_COMMON")==0){

            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }

            viewHolder.btContent.setText("총 학점");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);

        }
        else if (category.compareTo("SW_CONN_CUL")==0){

            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }

            viewHolder.btContent.setText("총 학점");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);

        } else if (category.compareTo("SW_CONN_TOTAL")==0){

            if (Integer.parseInt(grade) < Integer.parseInt(requirement) ){
                viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
            }else{
                viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
            }

            viewHolder.btContent.setText("총 학점");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText(requirement);

        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Gr> items)
    {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button btContent;
        TextView tvTest1,tvTest2,tvTest3,tvTest4;


        ViewHolder(View itemView) {
            super(itemView);
            btContent= itemView.findViewById(R.id.subject_class);

            tvTest1 = itemView.findViewById(R.id.test1);
            tvTest2 = itemView.findViewById(R.id.test2);
            tvTest3 = itemView.findViewById(R.id.test3);
            tvTest4 = itemView.findViewById(R.id.test4);
        }
    }
}
