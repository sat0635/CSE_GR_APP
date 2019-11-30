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
        Log.d("TAG","lopal"+item.getContent());
        viewHolder.tvTest1.setText("내 학점");
        viewHolder.tvTest2.setText("111");
        viewHolder.tvTest1.setText("총 학점");
        viewHolder.tvTest4.setText("112");
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

        TextView tvTest1,tvTest2,tvTest3,tvTest4;

        ViewHolder(View itemView) {
            super(itemView);
            tvTest1 = itemView.findViewById(R.id.test2);
            tvTest2 = itemView.findViewById(R.id.test2);
            tvTest3 = itemView.findViewById(R.id.test2);
            tvTest4 = itemView.findViewById(R.id.test4);
        }
    }
}
