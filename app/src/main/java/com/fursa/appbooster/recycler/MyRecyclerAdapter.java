package com.fursa.appbooster.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fursa.appbooster.app.MyApp;
import com.fursa.appbooster.R;
import com.fursa.appbooster.model.TaskModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerView adapter for MainActivity
 * Created by Fursa Ilya on 10.11.17.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<TaskModel> taskModelList;

    public MyRecyclerAdapter(List<TaskModel> taskModelList) {
        this.taskModelList = taskModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaskModel model = taskModelList.get(position);
        holder.mTitleText.setText(model.getTitle());
        holder.mRewardText.setText(String.valueOf(model.getReward()));
        Picasso.with(MyApp.getContext()).load(model.getIconUrl()).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return taskModelList.size();
    }
}
