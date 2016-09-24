package com.zriton.topprevents.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by aditya on 24/09/16.
 */

public class AboutFragment extends Fragment{

    public static AboutFragment newInstance() {
        Bundle arguments = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
}
