package com.fursa.appbooster.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fursa.appbooster.R;
import com.fursa.appbooster.app.MyApp;
import com.fursa.appbooster.model.TaskModel;
import com.squareup.picasso.Picasso;

/**
 * This fragment helps to show details
 * of each item of RecyclerView from
 * MainActivity
 * <p>
 * Created by Fursa Ilya on 10.11.17.
 */

public class DetailFragment extends Fragment implements View.OnClickListener {
    private TextView title;
    private TextView description;
    private TextView reward;
    private TextView downloadLink;
    private ImageView icon;
    private Button btnApp;
    private TaskModel model;

    private static final String TASK_TAG = "TaskObject";
    private static final String TAG = DetailFragment.class.getSimpleName();


    public static Fragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        reward = (TextView) view.findViewById(R.id.reward);
        downloadLink = (TextView) view.findViewById(R.id.download_link);
        icon = (ImageView) view.findViewById(R.id.icon);
        btnApp = (Button) view.findViewById(R.id.btnApp);

        btnApp.setOnClickListener(this);

        model = (TaskModel) getArguments().getSerializable(TASK_TAG);
        title.setText(model.getTitle());
        description.setText(model.getDescription());
        reward.setText(reward.getText() + " " + String.valueOf(model.getReward()));
        downloadLink.setText(model.getDownloadLink());
        Picasso.with(MyApp.getContext()).load(model.getIconUrl()).into(icon);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isAppInstalled = isAppInstalled(model.getDownloadLink());
        if(isAppInstalled == true) {
            btnApp.setText("Открыть");
            btnApp.setBackgroundColor(getResources().getColor(R.color.btn_open_color));
        } else {
            btnApp.setText("Установить");
            btnApp.setBackgroundColor(getResources().getColor(R.color.btn_install_color));

        }
    }

    /*
        if app installed run startActivity with intent to open app
        if app not installed run Google play store to download it
     */
    @Override
    public void onClick(View view) {
        if(!isAppInstalled(model.getDownloadLink())) {
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(model.getDownloadLink())));
        } else {
            Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage(getPackageInfo(model.getDownloadLink()));
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        }
    }
    /*
        This method returns boolean true if app installed on Android phone
     */
    private boolean isAppInstalled(String packageName) {
        PackageManager pm = MyApp.getContext().getPackageManager();
        boolean isInstalled = false;
        try {
            pm.getPackageInfo(getPackageInfo(model.getDownloadLink()), PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        Log.d(TAG, "isInstalled = " + isInstalled);
        return isInstalled;
    }
    /*
     This method helps to cut our link and get a package of android app
     */
    private String getPackageInfo(String downloadLink) {
        String pkg = downloadLink.substring(downloadLink.lastIndexOf("com"));
        return pkg;
    }


}
