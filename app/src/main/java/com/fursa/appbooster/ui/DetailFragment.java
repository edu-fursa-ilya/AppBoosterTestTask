package com.fursa.appbooster.ui;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private TextView appBundleId;
    private TextView appleDownloadLink;
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
        appBundleId = (TextView) view.findViewById(R.id.app_bundle_id);
        appleDownloadLink = (TextView) view.findViewById(R.id.apple_download_link);
        icon = (ImageView) view.findViewById(R.id.icon);
        btnApp = (Button) view.findViewById(R.id.btnApp);

        btnApp.setOnClickListener(this);

        model = (TaskModel) getArguments().getSerializable(TASK_TAG);
        title.setText(model.getTitle());
        description.setText(model.getDescription());
        reward.setText(String.valueOf(model.getReward()));
        downloadLink.setText(model.getDownloadLink());
        appBundleId.setText(model.getAppleBundleId());
        appleDownloadLink.setText(model.getAppleDownloadLink());
        Picasso.with(MyApp.getContext()).load(model.getIconUrl()).into(icon);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isAppInstalled = isAppInstalled(model.getDownloadLink());
        if(isAppInstalled == true) {
            btnApp.setBackgroundColor(Color.GREEN);
            btnApp.setText("Открыть");
        } else {
            btnApp.setBackgroundColor(Color.RED);
            btnApp.setText("Установить");
        }
    }


    @Override
    public void onClick(View view) {

    }

    private boolean isAppInstalled(String packageName) {
        String pkg = packageName.substring(packageName.lastIndexOf("com"));
        PackageManager pm = MyApp.getContext().getPackageManager();
        boolean isInstalled = false;
        try {
            pm.getPackageInfo(pkg, PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }


}
