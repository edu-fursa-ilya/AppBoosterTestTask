package com.fursa.appbooster.recycler;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fursa.appbooster.R;
import com.fursa.appbooster.app.FragmentHelper;
import com.fursa.appbooster.app.MyApp;
import com.fursa.appbooster.db.DBWorker;
import com.fursa.appbooster.model.TaskModel;
import com.fursa.appbooster.ui.MainActivity;

/**
 * Created by Fursa Ilya on 10.11.17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private DBWorker dbWorker;
    private static final String TASK_TAG = "TaskObject";
    private static final String TAG = MyViewHolder.class.getSimpleName();

    CardView mCard;
    TextView mTitleText;
    TextView mRewardText;
    ImageView icon;

    public MyViewHolder(View itemView) {
        super(itemView);
        mCard = (CardView) itemView.findViewById(R.id.cardTask);
        mTitleText = (TextView) itemView.findViewById(R.id.title);
        mRewardText = (TextView) itemView.findViewById(R.id.reward);
        icon = (ImageView) itemView.findViewById(R.id.icon);

        mCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dbWorker = new DBWorker(MyApp.getContext());
        TaskModel model = dbWorker.getTaskByTitle(mTitleText.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable(TASK_TAG, model);
        ((MainActivity)view.getContext()).onShowDetailFragmentListener(bundle);

    }
}
