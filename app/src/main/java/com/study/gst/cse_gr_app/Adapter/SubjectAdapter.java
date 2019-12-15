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
import com.study.gst.cse_gr_app.model.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>  {
    private ArrayList<Subject> items = new ArrayList<>();

    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder viewHolder, int position) {
        Subject item = items.get(position);
        String category = item.getCategory();
        String content = item.getContent().split(",")[0];
        String YN = item.getContent().split(",")[1];
        viewHolder.btContent.setText(category);
        viewHolder.tvTest1.setText(content);
        if(YN.compareTo("N")==0){
            viewHolder.btContent.setBackgroundColor(Color.rgb(200,0,0));
        }else{
            viewHolder.btContent.setBackgroundColor(Color.rgb(0,200,0));
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Subject> items)
    {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button btContent;
        TextView tvTest1;


        ViewHolder(View itemView) {
            super(itemView);
            btContent= itemView.findViewById(R.id.subject_class);

            tvTest1 = itemView.findViewById(R.id.test1);
        }
    }
}
