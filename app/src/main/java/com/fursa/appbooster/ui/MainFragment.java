package com.fursa.appbooster.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fursa.appbooster.R;
import com.fursa.appbooster.app.MyApp;
import com.fursa.appbooster.db.DBWorker;
import com.fursa.appbooster.model.TaskModel;
import com.fursa.appbooster.network.OffersFetcher;
import com.fursa.appbooster.recycler.MyRecyclerAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fursa Ilya on 12.11.17.
 */

public class MainFragment extends Fragment {
    private DBWorker dbWorker;
    private MyRecyclerAdapter mAdapter;
    //Views
    private RecyclerView mRecycler;
    private RelativeLayout parent;

    private static final String TAG = MainFragment.class.getSimpleName();

    public static Fragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        parent = (RelativeLayout) view.findViewById(R.id.progress);
        mRecycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(MyApp.getContext()));

        dbWorker = new DBWorker(MyApp.getContext());

        new FetchOfferItemsTask().execute();
        return view;
    }

    private class FetchOfferItemsTask extends AsyncTask<Void, Void, List<TaskModel>> {

        @Override
        protected List<TaskModel> doInBackground(Void... voids) {
            List<TaskModel> modelList = new ArrayList<>();
            OffersFetcher fetcher = new OffersFetcher();
            try {
               if(dbWorker.getTableSize() == 0) {
                   modelList = fetcher.fetchItems();
               } else {
                   modelList = dbWorker.getAllTasks();
               }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return modelList;
        }

        @Override
        protected void onPostExecute(List<TaskModel> taskModels) {
            if (dbWorker.getTableSize() <= 0) {
                showProgressBar();
                for (TaskModel model : taskModels) {
                    dbWorker.add(model);
                    Log.d(TAG, "Row inserted: " + model.toString());
                }
            }
            Log.d(TAG, "Rows size = " + dbWorker.getTableSize());

            mAdapter = new MyRecyclerAdapter(taskModels);
            mRecycler.setAdapter(mAdapter);
            hideProgressBar();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mRecycler.setAdapter(mAdapter);
    }


    private void hideProgressBar() {
        parent.setVisibility(View.INVISIBLE);
    }

    private void showProgressBar() {
        parent.setVisibility(View.VISIBLE);
    }

}
