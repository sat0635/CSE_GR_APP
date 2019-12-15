package com.study.gst.cse_gr_app.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String grade = String.valueOf(item.getGrade());
        //심화컴퓨터
        if (category.compareTo("MAJOR") == 0){
            viewHolder.tvSubjectClass.setText("전공");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("75");
        }
        else if (category.compareTo("BASEMAJOR") == 0){
            viewHolder.tvSubjectClass.setText("전공기반");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("22");
        }
        else if (category.compareTo("ENGINEER_CUL") == 0){
            viewHolder.tvSubjectClass.setText("기본소양");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("15");
        }

        //글로벌SW융합
        if (category.compareTo("SW_MAJOR") == 0){
            viewHolder.tvSubjectClass.setText("전공");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("51");
        }
        else if (category.compareTo("SW_CUL") == 0){
            viewHolder.tvSubjectClass.setText("교양");
            viewHolder.tvTest1.setText("내 학점");
            viewHolder.tvTest2.setText(grade);
            viewHolder.tvTest3.setText("졸업요건 학점");
            viewHolder.tvTest4.setText("24~42");
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

        TextView tvSubjectClass,tvTest1,tvTest2,tvTest3,tvTest4;


        ViewHolder(View itemView) {
            super(itemView);
            tvSubjectClass = itemView.findViewById(R.id.subject_class);
            tvTest1 = itemView.findViewById(R.id.test1);
            tvTest2 = itemView.findViewById(R.id.test2);
            tvTest3 = itemView.findViewById(R.id.test3);
            tvTest4 = itemView.findViewById(R.id.test4);
        }
    }
}
