package com.fursa.appbooster.app;


import android.os.Bundle;

import com.fursa.appbooster.ui.DetailFragment;

/**
 * Created by Fursa Ilya on 12.11.17.
 */

public class FragmentHelper {

    public interface IFragmentCallBack {
         void onShowDetailFragmentListener(Bundle bundle);
    }
}
