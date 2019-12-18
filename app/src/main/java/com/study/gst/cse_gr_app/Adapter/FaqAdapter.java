package com.study.gst.cse_gr_app.Adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.gst.cse_gr_app.R;
import com.study.gst.cse_gr_app.model.Gr;
import com.study.gst.cse_gr_app.model.Question;

import java.util.ArrayList;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder>  {
    private ArrayList<Question> items = new ArrayList<>();

    @NonNull
    @Override
    public FaqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.qa_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.ViewHolder viewHolder, int position) {
        Question item = items.get(position);
        viewHolder.ivImage1.setImageResource(R.drawable.user);
        viewHolder.tvText1.setText(item.getTitle());
        viewHolder.tvText2.setText(item.getDesc());
        viewHolder.tvText3.setText(item.getAnswer());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Question> items)
    {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText1,tvText2,tvText3;
        ImageView ivImage1;
        ViewHolder(View itemView) {
            super(itemView);
            ivImage1 = itemView.findViewById(R.id.imageView1);
            tvText1 = itemView.findViewById(R.id.textView1);
            tvText2 = itemView.findViewById(R.id.textView2);
            tvText3 = itemView.findViewById(R.id.textView3);
        }
    }
}
