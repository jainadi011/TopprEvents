package com.zriton.topprevents.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by aditya on 24/09/16.
 */

public class StatsFragment extends Fragment {

    public static StatsFragment newInstance() {
        Bundle arguments = new Bundle();
        StatsFragment fragment = new StatsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
}
