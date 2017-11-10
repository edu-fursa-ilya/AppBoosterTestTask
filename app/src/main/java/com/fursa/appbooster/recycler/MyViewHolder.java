package com.fursa.appbooster.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fursa.appbooster.app.MyApp;
import com.fursa.appbooster.R;

/**
 * Created by Fursa Ilya on 10.11.17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        Toast.makeText(MyApp.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
    }
}
