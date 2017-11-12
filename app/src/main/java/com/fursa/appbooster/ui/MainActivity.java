package com.fursa.appbooster.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fursa.appbooster.R;
import com.fursa.appbooster.app.FragmentHelper;


public class MainActivity extends AppCompatActivity implements FragmentHelper.IFragmentCallBack {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commit();
    }


    @Override
    public void onShowDetailFragmentListener(Bundle bundle) {
        Fragment fragment = DetailFragment.newInstance();
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, fragment)
                .commit();

        Log.d(TAG, "called: onShowDetailFragmentListener()");
    }





}
