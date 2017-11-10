package com.fursa.appbooster;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.fursa.appbooster.db.DBWorker;
import com.fursa.appbooster.model.TaskModel;
import com.fursa.appbooster.network.OffersFetcher;
import com.fursa.appbooster.recycler.MyRecyclerAdapter;

import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBWorker dbWorker;
    private MyRecyclerAdapter mAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    //Views
    private RecyclerView mRecycler;
    private RelativeLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (RelativeLayout) findViewById(R.id.progress);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        new FetchOfferItemsTask().execute();
    }

    private class FetchOfferItemsTask extends AsyncTask<Void, Void, List<TaskModel>> {

        @Override
        protected List<TaskModel> doInBackground(Void... voids) {
            List<TaskModel> modelList = new ArrayList<>();
            OffersFetcher fetcher = new OffersFetcher();
            try {
                modelList = fetcher.fetchItems();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return modelList;
        }

        @Override
        protected void onPostExecute(List<TaskModel> taskModels) {
            dbWorker = new DBWorker(getBaseContext());
            if (dbWorker.getRowsSize() <= 0) {
                showProgressBar();
                for (TaskModel model : taskModels) {
                    dbWorker.add(model);
                    Log.d(TAG, "Row inserted: " + model.toString());
                }
            }
            Log.d(TAG, "Rows size = " + dbWorker.getRowsSize());

            mAdapter = new MyRecyclerAdapter(taskModels);
            mRecycler.setAdapter(mAdapter);
            hideProgressBar();
        }
    }

    @Override
    protected void onPause() {
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
